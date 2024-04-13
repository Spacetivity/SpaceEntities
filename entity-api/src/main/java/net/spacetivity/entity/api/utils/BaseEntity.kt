package net.spacetivity.entity.api.utils

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.properties.EntityProperties
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*

interface BaseEntity {

    val key: String
    val uuid: UUID
    val entityId: Int
    val isCustomNameShown: Boolean
    var customName: Component
    var isVisible: Boolean
    val type: EntityType
    var location: Location
    val dataWatcher: WrappedDataWatcher

    val viewers: MutableSet<UUID>

    val properties: EntityProperties

    fun spawn(viewer: UUID)

    fun despawn(viewer: UUID)

    fun teleport(location: Location, viewer: UUID)

    fun addMetadata(metadata: EntityMetadata<*>, customValue: Any?) {
        this.properties.metadataStorage[metadata] = customValue
    }

    fun update(viewer: UUID) = EntityProvider.api.setEntityMetadata(this, viewer)

}