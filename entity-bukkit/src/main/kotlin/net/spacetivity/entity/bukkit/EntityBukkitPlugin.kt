package net.spacetivity.entity.bukkit

import com.comphenix.protocol.ProtocolLibrary
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.display.FakeTextDisplay
import net.spacetivity.entity.common.EntityApiImpl
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
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
        if (player.name != "TGamings") return

        println(NamedTextColor.RED.value())

        val textDisplay: FakeTextDisplay = EntityProvider.api.fakeTextDisplay("tgamings-test", player.location)
           // .backgroundColor(NamedTextColor.RED.value())
            .text(Component.text("Marco stinkt ganz doll!"))
            .billboard(true)
            .build()

        textDisplay.viewers.add(player.uniqueId)
        textDisplay.spawn(player.uniqueId)
    }
}