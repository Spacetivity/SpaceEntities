package net.spacetivity.entity.common.api.player

import com.comphenix.protocol.wrappers.EnumWrappers
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import com.destroystokyo.paper.profile.ProfileProperty
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.player.FakePlayer
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import java.util.*

class FakePlayerImpl(
    override val key: String,
    override val isCustomNameShown: Boolean,
    override val customName: Component,
    override val isVisible: Boolean,
    override val type: EntityType,
    override val location: Location,
    override val isLookingAtPlayer: Boolean,
    override val rotationActionDistance: Double,
    override val property: ProfileProperty,
    override val tablistRemovalTicks: Long,
    override val metadataStorage: MutableMap<EntityMetadata<*>, Any?>,
    override val equipment: MutableMap<EnumWrappers.ItemSlot, ItemStack>
) : FakePlayer {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    override fun spawn() {
        TODO("Not yet implemented")
    }

    override fun despawn() {
        TODO("Not yet implemented")
    }

    override fun hide(uuid: UUID) {
        TODO("Not yet implemented")
    }

    override fun show(uuid: UUID) {
        TODO("Not yet implemented")
    }

    override fun teleport(location: Location) {
        TODO("Not yet implemented")
    }

}