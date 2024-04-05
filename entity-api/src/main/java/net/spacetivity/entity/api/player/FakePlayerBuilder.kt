package net.spacetivity.entity.api.player

import com.comphenix.protocol.wrappers.EnumWrappers.ItemSlot
import com.destroystokyo.paper.profile.ProfileProperty
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.utils.BaseEntityBuilder
import org.bukkit.inventory.ItemStack

interface FakePlayerBuilder : BaseEntityBuilder<FakePlayer> {

    fun visible(isVisible: Boolean): FakePlayerBuilder

    fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): FakePlayerBuilder

    fun isRotationActive()

    fun addItem(itemSlot: ItemSlot, itemStack: ItemStack): FakePlayerBuilder

    fun tablistRemovalTicks(ticks: Long): FakePlayerBuilder

    fun lookingAtPlayer(): FakePlayerBuilder

    fun property(property: ProfileProperty): FakePlayerBuilder

}