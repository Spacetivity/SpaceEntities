package net.spacetivity.entity.api.properties

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.inventory.ItemStack

data class EntityProperties(
    val metadataStorage: MutableMap<EntityMetadata<*>, Any?>,
    val isRotationActive: Boolean,
    val rotationActionDistance: Double,
    val equipment: MutableMap<ItemSlot, ItemStack>
) {

    fun getItemInMainHand(): ItemStack? = getItemFromSlot(ItemSlot.MAINHAND)
    fun getItemInOffHand(): ItemStack? = getItemFromSlot(ItemSlot.OFFHAND)

    fun getItemFromSlot(itemSlot: ItemSlot): ItemStack? = this.equipment[itemSlot]

}