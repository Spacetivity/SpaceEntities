package net.spacetivity.entity.common.api.display

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.display.FakeTextDisplay
import net.spacetivity.entity.api.display.FakeTextDisplayBuilder
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.joml.Vector3f

class FakeTextDisplayBuilderImpl(private val key: String, private val location: Location) : FakeTextDisplayBuilder {

    private var isBillboard: Boolean = true
    private var translation: Vector3f = EntityMetadataRegistry.TextDisplay.TRANSLATION.defaultValue!!
    private var backgroundColor: Int = EntityMetadataRegistry.TextDisplay.BACKGROUND_COLOR.defaultValue!!
    private var textOpacity: Byte = EntityMetadataRegistry.TextDisplay.TEXT_OPACITY.defaultValue!!
    private var isSeeThrough: Boolean = true
    private var text: Component = Component.empty()

    private var properties = EntityProperties(mutableMapOf(), false, 5.0, mutableMapOf())

    override fun billboard(isBillboard: Boolean): FakeTextDisplayBuilder {
        this.isBillboard = isBillboard
        return this
    }

    override fun translation(translation: Vector3f): FakeTextDisplayBuilder {
        this.translation = translation
        return this
    }

    override fun backgroundColor(colorAsInt: Int): FakeTextDisplayBuilder {
        this.backgroundColor = colorAsInt
        return this
    }

    override fun transparentBackgroundColor(): FakeTextDisplayBuilder {
        this.backgroundColor = EntityMetadataRegistry.TextDisplay.TRANSPARENT_BACKGROUND_COLOR.defaultValue!!
        return this
    }

    override fun textOpacity(textOpacity: Byte): FakeTextDisplayBuilder {
        this.textOpacity = textOpacity
        return this
    }

    override fun seeThrough(): FakeTextDisplayBuilder {
        this.isSeeThrough = true
        return this
    }

    override fun text(text: Component): FakeTextDisplayBuilder {
        this.text = text
        return this
    }

    override fun properties(propertiesBuilder: EntityPropertiesBuilder): FakeTextDisplayBuilder {
        this.properties = propertiesBuilder.build()
        return this
    }

    override fun build(): FakeTextDisplay {
        return FakeTextDisplayImpl(
            this.key,
            isCustomNameShown = true,
            isVisible = true,
            EntityType.TEXT_DISPLAY,
            this.location,
            this.properties,
            this.isBillboard,
            this.translation,
            this.backgroundColor,
            this.textOpacity,
            this.isSeeThrough,
            this.text,
        )
    }

}