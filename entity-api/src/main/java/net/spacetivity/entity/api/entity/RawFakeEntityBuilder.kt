package net.spacetivity.entity.api.entity

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.api.utils.BaseEntityBuilder

interface RawFakeEntityBuilder : BaseEntityBuilder<RawFakeEntity> {

    fun customNameShown(isCustomNameShown: Boolean): RawFakeEntityBuilder
    fun customName(customName: Component): RawFakeEntityBuilder

    fun visible(isVisible: Boolean): RawFakeEntityBuilder

    fun properties(propertiesBuilder: EntityPropertiesBuilder): RawFakeEntityBuilder

}