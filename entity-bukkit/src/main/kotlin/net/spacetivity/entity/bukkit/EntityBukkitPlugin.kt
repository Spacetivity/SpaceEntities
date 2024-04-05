package net.spacetivity.entity.bukkit

import com.comphenix.protocol.ProtocolLibrary
import net.kyori.adventure.text.minimessage.MiniMessage
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.entity.RawFakeEntity
import net.spacetivity.entity.common.EntityApiImpl
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin

class EntityBukkitPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        EntityProvider.api = EntityApiImpl(ProtocolLibrary.getProtocolManager())

        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onJoin(event: PlayerInteractEvent) {
        val player: Player = event.player
        if (player.inventory.itemInMainHand.type != Material.STICK) return

        val fakeEntity: RawFakeEntity = EntityProvider.api.newFakeEntity("tgamings-test", EntityType.VILLAGER, player.location)
            .customNameShown(true)
            .customName(MiniMessage.miniMessage().deserialize("<gradient:aqua:green>Hello ${player.name}</gradient>"))

         //   .addMetadata(EntityMetadataVillager(17, WrappedVillagerData.fromValues(WrappedVillagerData.Type.SNOW, WrappedVillagerData.Profession.CLERIC, 1)))

//            .addMetadata(EntityMetadataRegistry.customMetadata(
//                17,
//                Any::class.javaObjectType,
//                null,
//                Registry.get(WrappedVillagerData.getNmsClass())
//            ), WrappedVillagerData.fromValues(WrappedVillagerData.Type.JUNGLE, WrappedVillagerData.Profession.TOOLSMITH, 1).handle)

            .build()

        fakeEntity.viewers.add(player.uniqueId)
        fakeEntity.spawn()
    }
}