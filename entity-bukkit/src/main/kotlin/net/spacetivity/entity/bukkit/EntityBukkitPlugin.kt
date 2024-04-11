package net.spacetivity.entity.bukkit

import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.wrappers.EnumWrappers
import net.kyori.adventure.text.minimessage.MiniMessage
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.entity.RawFakeEntity
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.player.FakePlayer
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.common.EntityApiImpl
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class EntityBukkitPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        EntityProvider.api = EntityApiImpl(this, ProtocolLibrary.getProtocolManager())

        this.server.pluginManager.registerEvents(this, this)
    }


    @EventHandler
    fun onJoin(event: PlayerInteractEvent) {
        val player: Player = event.player
        if (player.inventory.itemInMainHand.type != Material.STICK) return

        val fakeEntity: RawFakeEntity = EntityProvider.api.newFakeEntity("tgamings-test", EntityType.VILLAGER, player.location.add(1.0,0.0,2.0))
            .customNameShown(true)
            .customName(MiniMessage.miniMessage().deserialize("<gradient:aqua:green>Hello ${player.name}</gradient>"))
            .properties(EntityPropertiesBuilder.builder()
                .rotationActive()
                .rotationActionDistance(20.0)
                .equipItemStack(EnumWrappers.ItemSlot.MAINHAND, ItemStack(Material.GOLDEN_SWORD))
                .addMetadata(EntityMetadataRegistry.Global.GLOWING))
            .visible(true)
            .build()

        fakeEntity.viewers.add(player.uniqueId)
        fakeEntity.spawn(player.uniqueId)

        val fakePlayer: FakePlayer = EntityProvider.api.newFakePlayer("tgamings-test", player.location, player.playerProfile)
            .properties(EntityPropertiesBuilder.builder()
              //  .equipItemStack(EnumWrappers.ItemSlot.CHEST, ItemStack(Material.GOLDEN_CHESTPLATE))
                .addMetadata(EntityMetadataRegistry.Global.GLOWING))
            .build()

        fakePlayer.viewers.add(player.uniqueId)
        fakePlayer.spawn(player.uniqueId)

    }
}