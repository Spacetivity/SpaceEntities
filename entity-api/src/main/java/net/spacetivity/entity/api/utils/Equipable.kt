package net.spacetivity.entity.api.utils

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import org.bukkit.inventory.ItemStack

interface Equipable {

    val equipment: MutableMap<ItemSlot, ItemStack>

    fun getItemInMainHand(): ItemStack? = getItemFromSlot(ItemSlot.MAINHAND)
    fun getItemInOffHand(): ItemStack? = getItemFromSlot(ItemSlot.OFFHAND)

    fun getItemFromSlot(itemSlot: ItemSlot): ItemStack? = this.equipment[itemSlot]

}