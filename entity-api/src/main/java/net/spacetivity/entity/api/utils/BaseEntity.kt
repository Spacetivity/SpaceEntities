package net.spacetivity.entity.api.utils

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*

interface BaseEntity {

    val key: String
    val uuid: UUID
    val entityId: Int
    val isCustomNameShown: Boolean
    val customName: Component
    val isVisible: Boolean
    val type: EntityType
    val location: Location
    val dataWatcher: WrappedDataWatcher

    val metadataStorage: MutableMap<EntityMetadata<*>, Any?>
    val viewers: MutableSet<UUID>

    fun spawn()

    fun despawn()

    fun hide(uuid: UUID)

    fun show(uuid: UUID)

    fun teleport(location: Location)

}