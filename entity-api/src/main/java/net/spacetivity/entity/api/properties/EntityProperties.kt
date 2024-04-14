package net.spacetivity.entity.api.properties

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import net.spacetivity.entity.api.event.FakeEntityInteractEvent
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.inventory.ItemStack
import java.util.function.Consumer

data class EntityProperties(
    val metadataStorage: MutableMap<EntityMetadata<*>, Any?>,
    val isRotationActive: Boolean,
    val rotationActionDistance: Double,
    val equipment: MutableMap<ItemSlot, ItemStack>,
    val action: Consumer<FakeEntityInteractEvent>?
) {

    fun getItemInMainHand(): ItemStack? = getItemFromSlot(ItemSlot.MAINHAND)
    fun getItemInOffHand(): ItemStack? = getItemFromSlot(ItemSlot.OFFHAND)

    fun getItemFromSlot(itemSlot: ItemSlot): ItemStack? = this.equipment[itemSlot]

}