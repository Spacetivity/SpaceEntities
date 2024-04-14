package net.spacetivity.entity.api.properties

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.event.FakeEntityInteractEvent
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

interface EntityPropertiesBuilder {

    companion object {
        fun builder(): EntityPropertiesBuilder = EntityProvider.api.newEntityProperties()
    }

    fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): EntityPropertiesBuilder

    fun rotationActive(): EntityPropertiesBuilder

    fun rotationActionDistance(distance: Double): EntityPropertiesBuilder

    fun equipItemStack(itemSlot: ItemSlot, itemStack: ItemStack): EntityPropertiesBuilder

    fun interaction(action: Consumer<FakeEntityInteractEvent>): EntityPropertiesBuilder

    fun build(): EntityProperties

}