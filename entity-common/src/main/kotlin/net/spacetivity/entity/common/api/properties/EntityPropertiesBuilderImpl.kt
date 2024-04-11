package net.spacetivity.entity.common.api.properties

import com.comphenix.protocol.wrappers.EnumWrappers
import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import org.bukkit.inventory.ItemStack

class EntityPropertiesBuilderImpl : EntityPropertiesBuilder {

    private val metadataStorage: MutableMap<EntityMetadata<*>, Any?> = mutableMapOf()
    private val equipment: MutableMap<ItemSlot, ItemStack> = mutableMapOf()

    private var rotationActive: Boolean = false
    private var rotationActionDistance: Double = 5.0

    override fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): EntityPropertiesBuilder {
        this.metadataStorage[metadata] = customValue
        return this
    }

    override fun rotationActive(): EntityPropertiesBuilder {
        this.rotationActive = true
        return this
    }

    override fun rotationActionDistance(distance: Double): EntityPropertiesBuilder {
        this.rotationActionDistance = distance
        return this
    }

    override fun equipItemStack(itemSlot: EnumWrappers.ItemSlot, itemStack: ItemStack): EntityPropertiesBuilder {
        this.equipment[itemSlot] = itemStack
        return this
    }

    override fun build(): EntityProperties {
        return EntityProperties(this.metadataStorage, this.rotationActive, this.rotationActionDistance, this.equipment)
    }

}