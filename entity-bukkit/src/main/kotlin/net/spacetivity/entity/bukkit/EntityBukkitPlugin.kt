package net.spacetivity.entity.bukkit

import com.comphenix.protocol.ProtocolLibrary
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.armorstand.FakeArmorStand
import net.spacetivity.entity.common.EntityApiImpl
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.plugin.java.JavaPlugin
import org.joml.Vector3f

class EntityBukkitPlugin : JavaPlugin(), Listener {

    override fun onEnable() {
        EntityProvider.api = EntityApiImpl(this, ProtocolLibrary.getProtocolManager())

        this.server.pluginManager.registerEvents(this, this)
    }


    @EventHandler
    fun onJoin(event: PlayerInteractEvent) {
        val player: Player = event.player

        if (player.inventory.itemInMainHand.type != Material.STICK) return
        if (player.name != "TGamings") return

        val fakeArmorStand: FakeArmorStand = EntityProvider.api.fakeArmorStand("tgamings-test", player.location.add(1.0, 0.0, 2.0))
            .customNameShown(true)
            .customName(Component.text("Hello World"))
            .leftLegRotation(Vector3f(0.0F, 1.5F, 2.4F))
            .noBasePlate()
            .enableArms()
            .setSmall()
            .build()

        fakeArmorStand.viewers.add(player.uniqueId)
        fakeArmorStand.spawn(player.uniqueId)
    }
}