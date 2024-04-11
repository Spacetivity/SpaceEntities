package net.spacetivity.entity.api.properties

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.inventory.ItemStack

interface EntityPropertiesBuilder {

    companion object {
        fun builder(): EntityPropertiesBuilder = EntityProvider.api.newEntityProperties()
    }

    fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): EntityPropertiesBuilder

    fun rotationActive(): EntityPropertiesBuilder

    fun rotationActionDistance(distance: Double): EntityPropertiesBuilder

    fun equipItemStack(itemSlot: ItemSlot, itemStack: ItemStack): EntityPropertiesBuilder

    fun build(): EntityProperties

}