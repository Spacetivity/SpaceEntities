package net.spacetivity.entity.common.api.display

import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.display.FakeTextDisplay
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.joml.Vector3f
import java.util.*

class FakeTextDisplayImpl(
    override val key: String,
    override val isCustomNameShown: Boolean = true,
    override var isVisible: Boolean,
    override val type: EntityType,
    override var location: Location,
    override val properties: EntityProperties,
    override var billboardConstraints: Boolean,
    override var translation: Vector3f,
    override var backgroundColor: Int,
    override var textOpacity: Byte,
    override var isSeeThrough: Boolean,
    override var text: Component,
) : FakeTextDisplay {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    init {
        val textDisplayData = EntityMetadataRegistry.TextDisplay

        if (this.billboardConstraints) addMetadata(textDisplayData.BILLBOARD_CONSTRAINTS, null)

        addMetadata(textDisplayData.TRANSLATION, this.translation)
        addMetadata(textDisplayData.BACKGROUND_COLOR, this.backgroundColor)
        addMetadata(textDisplayData.TEXT_OPACITY, this.textOpacity)

        if (this.isSeeThrough) addMetadata(textDisplayData.TEXT_OPACITY, null)

        addMetadata(textDisplayData.TEXT, WrappedChatComponent.fromJson(GsonComponentSerializer.gson().serialize(this.customName)))
    }

    override fun spawn(viewer: UUID) {
        EntityUtils.spawn(this, viewer)
    }

    override fun despawn(viewer: UUID) {
        EntityUtils.despawn(this, viewer)
    }

    override fun teleport(location: Location, viewer: UUID) {
        EntityUtils.teleport(this, location, viewer)
    }

}