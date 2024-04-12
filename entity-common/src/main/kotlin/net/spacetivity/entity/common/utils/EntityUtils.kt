@file:Suppress("DEPRECATION")

package net.spacetivity.entity.common.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.InternalStructure
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.reflect.StructureModifier
import com.comphenix.protocol.wrappers.*
import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.metadata.type.EntityMetadataByte
import net.spacetivity.entity.api.metadata.type.EntityMetadataCustomName
import net.spacetivity.entity.api.utils.BaseEntity
import net.spacetivity.entity.common.api.armorstand.FakeArmorStandImpl
import net.spacetivity.entity.common.api.player.FakePlayerImpl
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.*
import kotlin.math.min

object EntityUtils {

    private val packetManager: ProtocolManager = EntityProvider.api.protocolManager

    fun spawn(baseEntity: BaseEntity, uuid: UUID) {
        val viewer: Player = Bukkit.getPlayer(uuid) ?: return

        if (baseEntity is FakePlayerImpl) setPlayerInfo(baseEntity, viewer, false)

        val packetContainer: PacketContainer = this.packetManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY)

        packetContainer.integers.write(0, baseEntity.entityId)
        packetContainer.uuiDs.write(0, baseEntity.uuid)
        packetContainer.doubles.write(0, baseEntity.location.x)
        packetContainer.doubles.write(1, baseEntity.location.y)
        packetContainer.doubles.write(2, baseEntity.location.z)
        packetContainer.entityTypeModifier.write(0, baseEntity.type)

        packetContainer.bytes.write(0, (baseEntity.location.pitch * 256F / 360F).toInt().toByte())
        packetContainer.bytes.write(2, (baseEntity.location.yaw * 256F / 360F).toInt().toByte())

        this.packetManager.sendServerPacket(viewer, packetContainer)

        setMetadata(baseEntity)
        setEquipment(baseEntity, viewer)

        if (baseEntity is FakePlayerImpl || baseEntity is FakeArmorStandImpl)
            setRotation(baseEntity, viewer)

        if (baseEntity is FakePlayerImpl) {
            removeFakePlayerName(baseEntity, viewer)

            Bukkit.getScheduler().runTaskLater(EntityProvider.api.plugin, Runnable {
                setPlayerInfo(baseEntity, viewer, true)
            }, baseEntity.tablistRemovalTicks)
        }
    }

    fun despawn(baseEntity: BaseEntity, uuid: UUID) {
        val packetContainer: PacketContainer = this.packetManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY)
        packetContainer.intLists.write(0, arrayListOf(baseEntity.entityId))

        val viewer: Player = Bukkit.getPlayer(uuid) ?: return

        this.packetManager.sendServerPacket(viewer, packetContainer)
        baseEntity.viewers.remove(viewer.uniqueId)
    }

    private fun setMetadata(baseEntity: BaseEntity) {
        val packetContainer: PacketContainer = packetManager.createPacket(PacketType.Play.Server.ENTITY_METADATA)

        packetContainer.integers.write(0, baseEntity.entityId)

        var globalByteFlags = 0
        var armorStandByteFlags = 0

        for (mutableEntry: MutableMap.MutableEntry<EntityMetadata<*>, Any?> in baseEntity.properties.metadataStorage) {
            val metadata: EntityMetadata<*> = mutableEntry.key
            val customValue: Any? = mutableEntry.value

            if (metadata.index == 0 && metadata is EntityMetadataByte) {
                val bitFlag: Byte = metadata.defaultValue ?: continue
                globalByteFlags = globalByteFlags or bitFlag.toInt()
            } else if (metadata.index == 15 && metadata is EntityMetadataByte) {
                val bitFlag: Byte = metadata.defaultValue ?: continue
                armorStandByteFlags = armorStandByteFlags or bitFlag.toInt()
            } else {
                val safeValue: Any = customValue ?: (metadata.defaultValue ?: continue)

                if (baseEntity.isCustomNameShown && metadata is EntityMetadataCustomName) {
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(metadata.index, metadata.serializer), Optional.of(safeValue))
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean::class.javaObjectType)), true)
                } else {
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(metadata.index, metadata.serializer), safeValue)
                }
            }
        }

        if (baseEntity is FakePlayerImpl) {
            var fakePlayerByteFlags = 0

            for (skinPartData: EntityMetadataByte in EntityMetadataRegistry.FakePlayer.elements.map { it as EntityMetadataByte }) {
                val bitFlag: Byte = skinPartData.defaultValue ?: continue
                fakePlayerByteFlags = globalByteFlags or bitFlag.toInt()
            }

            baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(17, WrappedDataWatcher.Registry.get(Byte::class.javaObjectType)), fakePlayerByteFlags.toByte())
        }

        baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte::class.javaObjectType)), globalByteFlags.toByte())

        if (baseEntity is FakeArmorStandImpl)
            baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(15, WrappedDataWatcher.Registry.get(Byte::class.javaObjectType)), armorStandByteFlags.toByte())

        val wrappedDataValueList: ArrayList<WrappedDataValue> = arrayListOf()

        for (watchableObject: WrappedWatchableObject in baseEntity.dataWatcher.watchableObjects) {
            val watcherObject: WrappedDataWatcherObject = watchableObject.watcherObject
            wrappedDataValueList.add(WrappedDataValue(watcherObject.index, watcherObject.serializer, watchableObject.rawValue))
        }

        packetContainer.dataValueCollectionModifier.write(0, wrappedDataValueList)

        for (viewer: Player in baseEntity.viewers.mapNotNull { Bukkit.getPlayer(it) }) {
            packetManager.sendServerPacket(viewer, packetContainer)
        }
    }

    private fun setEquipment(baseEntity: BaseEntity, viewer: Player) {
        if (baseEntity.properties.equipment.isEmpty()) return

        val items: MutableList<Pair<ItemSlot, ItemStack>> = ArrayList()
        val packetContainer = PacketContainer(PacketType.Play.Server.ENTITY_EQUIPMENT)

        baseEntity.properties.equipment.forEach { (itemSlot: ItemSlot, itemStack: ItemStack) -> items.add(Pair(itemSlot, itemStack)) }

        packetContainer.integers.write(0, baseEntity.entityId)
        packetContainer.slotStackPairLists.write(0, items)

        this.packetManager.sendServerPacket(viewer, packetContainer)
    }

    private fun setRotation(baseEntity: BaseEntity, viewer: Player) {
        val lookPacket = PacketContainer(PacketType.Play.Server.ENTITY_LOOK)
        lookPacket.integers.write(0, baseEntity.entityId)
        lookPacket.bytes.write(0, (baseEntity.location.yaw * 256.0f / 360.0f).toInt().toByte())
        lookPacket.bytes.write(1, (baseEntity.location.pitch * 256.0f / 360.0f).toInt().toByte())
        lookPacket.booleans.write(0, true)
        this.packetManager.sendServerPacket(viewer, lookPacket)

        val rotationPacket = PacketContainer(PacketType.Play.Server.ENTITY_HEAD_ROTATION)
        rotationPacket.integers.write(0, baseEntity.entityId)
        rotationPacket.bytes.write(0, (baseEntity.location.yaw * 256.0f / 360.0f).toInt().toByte())
        this.packetManager.sendServerPacket(viewer, rotationPacket)
    }

    private fun setPlayerInfo(fakePlayer: FakePlayerImpl, viewer: Player, isRemoval: Boolean) {
        val packetType: PacketType = if (isRemoval) PacketType.Play.Server.PLAYER_INFO_REMOVE else PacketType.Play.Server.PLAYER_INFO
        val packetContainer: PacketContainer = this.packetManager.createPacket(packetType)

        if (isRemoval) {
            packetContainer.uuidLists.write(0, Collections.singletonList(fakePlayer.uuid))
        } else {
            packetContainer.playerInfoActions.write(0, EnumSet.of(EnumWrappers.PlayerInfoAction.ADD_PLAYER))
            packetContainer.playerInfoDataLists.write(1, Collections.singletonList(
                PlayerInfoData(
                    fakePlayer.gameProfile.uuid,
                    20,
                    false,
                    EnumWrappers.NativeGameMode.CREATIVE,
                    fakePlayer.gameProfile,
                    WrappedChatComponent.fromText("")
                )
            ))
        }

        this.packetManager.sendServerPacket(viewer, packetContainer)
    }

    private fun removeFakePlayerName(fakePlayer: FakePlayerImpl, viewer: Player) {
        val packet = PacketContainer(PacketType.Play.Server.SCOREBOARD_TEAM)

        val rawTeamName: String = fakePlayer.entityId.toString() + "nw-npc-team"
        val uniqueTeamName = rawTeamName.substring(0, min(rawTeamName.length.toDouble(), 16.0).toInt())
        packet.strings.write(0, uniqueTeamName)

        packet.integers.write(0, 0)

        packet.optionalStructures.read(0).ifPresent { structure: InternalStructure ->
            structure.chatComponents.write(0, WrappedChatComponent.fromText(""))
            structure.chatComponents.write(1, WrappedChatComponent.fromText(""))
            structure.chatComponents.write(2, WrappedChatComponent.fromText(""))

            structure.strings.write(0, "never")
            structure.strings.write(1, "always")

            val enumModifier: StructureModifier<ChatColor> = structure.getEnumModifier(ChatColor::class.java, 5)
            enumModifier.write(0, ChatColor.RESET)

            structure.integers.write(0, 1)
            packet.optionalStructures.write(0, Optional.of(structure))
            packet.getSpecificModifier(Collection::class.java).write(0, listOf<String>(Objects.requireNonNull(fakePlayer.gameProfile.name)))
        }

        this.packetManager.sendServerPacket(viewer, packet)
    }

}