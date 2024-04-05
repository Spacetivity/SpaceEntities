package net.spacetivity.entity.api.entity

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.utils.BaseEntityBuilder

interface RawFakeEntityBuilder : BaseEntityBuilder<RawFakeEntity> {

    fun customNameShown(isCustomNameShown: Boolean): RawFakeEntityBuilder

    fun customName(customName: Component): RawFakeEntityBuilder

    fun visible(isVisible: Boolean): RawFakeEntityBuilder

    fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): RawFakeEntityBuilder // if custom value has no entries, use default metadata value

}