package net.spacetivity.entity.common.api.entity

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.entity.RawFakeEntity
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.bukkit.Location
import org.bukkit.entity.EntityType

class RawFakeEntityBuilderImpl(private val key: String, private val location: Location, private val type: EntityType) : RawFakeEntityBuilder {

    private var isCustomNameShown: Boolean = false
    private var customName: Component = Component.empty()
    private var isVisible: Boolean = true

    private val metadataStorage: MutableMap<EntityMetadata<*>, Any?> = mutableMapOf()

    override fun customNameShown(isCustomNameShown: Boolean): RawFakeEntityBuilder {
        this.isCustomNameShown = isCustomNameShown
        return this
    }

    override fun customName(customName: Component): RawFakeEntityBuilder {
        this.customName = customName
        return this
    }

    override fun visible(isVisible: Boolean): RawFakeEntityBuilder {
        this.isVisible = isVisible
        return this
    }

    override fun <T> addMetadata(metadata: EntityMetadata<T>, vararg customValue: T?): RawFakeEntityBuilder {
        this.metadataStorage[metadata] = customValue
        return this
    }

    override fun build(): RawFakeEntity {
        return RawFakeEntityImpl(this.key, this.isCustomNameShown, this.customName, this.isVisible, this.type, this.location, this.metadataStorage)
    }

}