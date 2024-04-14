package net.spacetivity.entity.api.event

import com.comphenix.protocol.wrappers.EnumWrappers.EntityUseAction
import net.spacetivity.entity.api.utils.BaseEntity
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

class FakeEntityInteractEvent(player: Player, entity: BaseEntity, private val action: EntityUseAction) : PlayerEvent(player) {

    override fun getHandlers(): HandlerList = handlerList

    companion object {
        @JvmField
        val handlerList = HandlerList()
    }

}