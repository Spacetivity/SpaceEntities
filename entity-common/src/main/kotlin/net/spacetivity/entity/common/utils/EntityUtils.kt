package net.spacetivity.entity.common.utils

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.PacketContainer
import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedDataValue
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import com.comphenix.protocol.wrappers.WrappedDataWatcher.WrappedDataWatcherObject
import com.comphenix.protocol.wrappers.WrappedWatchableObject
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.metadata.type.EntityMetadataByte
import net.spacetivity.entity.api.utils.BaseEntity
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*

object EntityUtils {

    private val packetManager: ProtocolManager = EntityProvider.api.protocolManager

    fun spawn(baseEntity: BaseEntity) {
        val packetContainer: PacketContainer = packetManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY)

        packetContainer.integers.write(0, baseEntity.entityId)
        packetContainer.uuiDs.write(0, baseEntity.uuid)
        packetContainer.doubles.write(0, baseEntity.location.x)
        packetContainer.doubles.write(1, baseEntity.location.y)
        packetContainer.doubles.write(2, baseEntity.location.z)
        packetContainer.entityTypeModifier.write(0, baseEntity.type)

        packetContainer.bytes.write(0, (baseEntity.location.pitch * 256F / 360F).toInt().toByte())
        packetContainer.bytes.write(2, (baseEntity.location.yaw * 256F / 360F).toInt().toByte())

        for (viewer: Player in baseEntity.viewers.mapNotNull { Bukkit.getPlayer(it) }) {
            packetManager.sendServerPacket(viewer, packetContainer)
        }

        setMetadata(baseEntity)
    }

    private fun setMetadata(baseEntity: BaseEntity) {
        val packetContainer: PacketContainer = packetManager.createPacket(PacketType.Play.Server.ENTITY_METADATA)

        packetContainer.integers.write(0, baseEntity.entityId)

        var byteFlags = 0

        for (mutableEntry: MutableMap.MutableEntry<EntityMetadata<*>, Any?> in baseEntity.metadataStorage) {
            val metadata: EntityMetadata<*> = mutableEntry.key
            val customValue: Any? = mutableEntry.value

            if (metadata.index == 0 && metadata is EntityMetadataByte) {
                val bitFlag: Byte = metadata.defaultValue ?: continue
                byteFlags = byteFlags or bitFlag.toInt()
            } else {
                val safeValue: Any = customValue ?: (metadata.defaultValue ?: continue)

                if (baseEntity.isCustomNameShown && safeValue is WrappedChatComponent) {
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(2, WrappedDataWatcher.Registry.getChatComponentSerializer(true)), Optional.of(safeValue))
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(3, WrappedDataWatcher.Registry.get(Boolean::class.javaObjectType)), true)
                } else {
                    baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(metadata.index, metadata.serializer), safeValue)
                }
            }
        }

//        val villagerData: WrappedVillagerData = WrappedVillagerData.fromValues(WrappedVillagerData.Type.JUNGLE, WrappedVillagerData.Profession.CLERIC, 1)
//        baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(17, WrappedDataWatcher.Registry.get(WrappedVillagerData.getNmsClass())), villagerData.handle)

        baseEntity.dataWatcher.setObject(WrappedDataWatcherObject(0, WrappedDataWatcher.Registry.get(Byte::class.javaObjectType)), byteFlags.toByte())

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


}