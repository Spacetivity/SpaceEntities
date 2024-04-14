package net.spacetivity.entity.common.api.entity

import com.comphenix.protocol.wrappers.AdventureComponentConverter
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.entity.RawFakeEntity
import net.spacetivity.entity.api.event.FakeEntityInteractEvent
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*
import java.util.function.Consumer

class RawFakeEntityImpl(
    override val key: String,
    override val isCustomNameShown: Boolean,
    override var customName: Component,
    override var isVisible: Boolean,
    override val type: EntityType,
    override var location: Location,
    override val properties: EntityProperties
) : RawFakeEntity {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    init {
        if (this.isCustomNameShown) {
            addMetadata(EntityMetadataRegistry.Global.CUSTOM_NAME, AdventureComponentConverter.fromComponent(this.customName))
        }
    }

    override fun spawn(viewer: UUID) {
        EntityUtils.spawn(this, viewer)
    }

    override fun despawn(viewer: UUID) {
        EntityUtils.despawn(this, viewer)
    }

    override fun teleport(location: Location, viewer: UUID) {
        EntityUtils.teleport(this, location, viewer)
    }

}