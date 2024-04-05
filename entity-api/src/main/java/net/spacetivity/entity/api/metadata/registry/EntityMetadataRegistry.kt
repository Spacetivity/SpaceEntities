package net.spacetivity.entity.api.metadata.registry

import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.spacetivity.entity.api.metadata.EntityMetadata
import net.spacetivity.entity.api.metadata.type.*
import org.joml.Vector3f

class EntityMetadataRegistry {

    companion object {
        fun <T> customMetadata(index: Int, clazz: Class<T>, defaultValue: T?, serializer: Serializer): EntityMetadata<T> {
            return EntityMetadata(index, clazz, defaultValue, serializer)
        }
    }

    object Global {
        val CUSTOM_NAME = EntityMetadataComponent(2, null)

        val INVISIBLE = EntityMetadataByte(0, 0x20.toByte())
        val NO_GRAVITY = EntityMetadataBoolean(5, false)
        val IS_ON_FIRE = EntityMetadataByte(0, 0x01.toByte())
        val IS_CROUCHING = EntityMetadataByte(0, 0x02.toByte())
        val IS_SPRINTING = EntityMetadataByte(0, 0x08.toByte())
        val IS_SWIMMING = EntityMetadataByte(0, 0x10.toByte())
        val GLOWING = EntityMetadataByte(0, 0x40.toByte())
        val FLYING_ELYTRA = EntityMetadataByte(0, 0x80.toByte())
    }

    object ArmorStand {
        val NO_BASEPLATE = EntityMetadataByte(15, 0x08.toByte())
        val HAS_ARMS = EntityMetadataByte(15, 0x04.toByte())
        val IS_BABY = EntityMetadataBoolean(16, true)
        val HEAD_ROTATION = EntityMetadataVector3f(16, null)
        val BODY_ROTATION = EntityMetadataVector3f(17, null)
        val LEFT_ARM_ROTATION = EntityMetadataVector3f(18, null)
        val RIGHT_ARM_ROTATION = EntityMetadataVector3f(19, null)
        val LEFT_LEG_ROTATION = EntityMetadataVector3f(20, null)
        val RIGHT_LEG_ROTATION = EntityMetadataVector3f(21, null)
    }

    object FakePlayer {
        val CAPE_ENABLED = EntityMetadataByte(17, 0x01.toByte())
        val JACKET_ENABLED = EntityMetadataByte(17, 0x02.toByte())
        val LEFT_SLEEVE_ENABLED = EntityMetadataByte(17, 0x04.toByte())
        val RIGHT_SLEEVE_ENABLED = EntityMetadataByte(17, 0x08.toByte())
        val LEFT_PANTS_LEG_ENABLED = EntityMetadataByte(17, 0x10.toByte())
        val RIGHT_PANTS_LEG_ENABLED = EntityMetadataByte(17, 0x20.toByte())
        val HAT_ENABLED = EntityMetadataByte(17, 0x40.toByte())
    }

    object TextDisplay {
        val BILLBOARD_CONSTRAINTS = EntityMetadataByte(15, 1.toByte())
        val TRANSLATION = EntityMetadataVector3f(11, Vector3f(0f, 1f, 0f))
        val TEXT = EntityMetadataComponent(23, WrappedChatComponent.fromJson(GsonComponentSerializer.gson().serialize(Component.empty())))
        val BACKGROUND_COLOR = EntityMetadataInt(25, 0x40000000)
        val TRANSPARENT_BACKGROUND_COLOR = EntityMetadataInt(25, 0x00000000)
        val TEXT_OPACITY = EntityMetadataByte(26, (-1).toByte())
        val HAS_SHADOW = EntityMetadataByte(27, 0x01.toByte())
        val IS_SEE_THROUGH = EntityMetadataByte(27, 0x02.toByte())
        val USE_DEFAULT_BACKGROUND_COLOR = EntityMetadataByte(27, 0x04.toByte())
    }

}