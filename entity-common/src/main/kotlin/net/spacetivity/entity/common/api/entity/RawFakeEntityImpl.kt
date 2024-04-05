package net.spacetivity.entity.common.api.entity

import com.comphenix.protocol.wrappers.AdventureComponentConverter
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.entity.RawFakeEntity
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*

class RawFakeEntityImpl(
    override val key: String,
    override val isCustomNameShown: Boolean,
    override val customName: Component,
    override val isVisible: Boolean,
    override val type: EntityType,
    override val location: Location,
    override val metadataStorage: MutableMap<EntityMetadata<*>, Any?>
) : RawFakeEntity {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    init {
        if (this.isCustomNameShown) {
            this.metadataStorage[EntityMetadataRegistry.Global.CUSTOM_NAME] = AdventureComponentConverter.fromComponent(this.customName)
        }
    }

    override fun spawn() {
        EntityUtils.spawn(this)
    }

    override fun despawn() {

    }

    override fun hide(uuid: UUID) {

    }

    override fun show(uuid: UUID) {

    }

    override fun teleport(location: Location) {

    }

}