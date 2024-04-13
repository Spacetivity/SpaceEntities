package net.spacetivity.entity.api.display

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.api.utils.BaseEntityBuilder
import org.joml.Vector3f

interface FakeTextDisplayBuilder : BaseEntityBuilder<FakeTextDisplay> {

    fun billboard(isBillboard: Boolean): FakeTextDisplayBuilder

    fun translation(translation: Vector3f): FakeTextDisplayBuilder

    fun backgroundColor(colorAsInt: Int): FakeTextDisplayBuilder

    fun transparentBackgroundColor(): FakeTextDisplayBuilder

    fun textOpacity(textOpacity: Byte): FakeTextDisplayBuilder

    fun seeThrough(): FakeTextDisplayBuilder

    fun text(text: Component): FakeTextDisplayBuilder

    fun properties(propertiesBuilder: EntityPropertiesBuilder): FakeTextDisplayBuilder

}