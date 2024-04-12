package net.spacetivity.entity.common.api.armorstand

import com.comphenix.protocol.wrappers.AdventureComponentConverter
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.armorstand.FakeArmorStand
import net.spacetivity.entity.api.metadata.registry.EntityMetadataRegistry
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.joml.Vector3f
import java.util.*

class FakeArmorStandImpl(
    override val key: String,
    override val isCustomNameShown: Boolean,
    override val customName: Component,
    override val isVisible: Boolean,
    override val type: EntityType,
    override val location: Location,
    override val properties: EntityProperties,
    override val headRotation: Vector3f,
    override val bodyRotation: Vector3f,
    override val leftArmRotation: Vector3f,
    override val rightArmRotation: Vector3f,
    override val leftLegRotation: Vector3f,
    override val rightLegRotation: Vector3f,
    override val noBasePlate: Boolean,
    override val hasArms: Boolean,
    override val isSmall: Boolean,
    override val isMarker: Boolean
) : FakeArmorStand {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    init {
        if (this.isCustomNameShown) {
            this.properties.metadataStorage[EntityMetadataRegistry.Global.CUSTOM_NAME] = AdventureComponentConverter.fromComponent(this.customName)
        }

        val armorStandData = EntityMetadataRegistry.ArmorStand

        this.properties.metadataStorage[armorStandData.HEAD_ROTATION] = this.headRotation
        this.properties.metadataStorage[armorStandData.BODY_ROTATION] = this.bodyRotation

        this.properties.metadataStorage[armorStandData.LEFT_ARM_ROTATION] = this.leftArmRotation
        this.properties.metadataStorage[armorStandData.RIGHT_ARM_ROTATION] = this.rightArmRotation

        this.properties.metadataStorage[armorStandData.LEFT_LEG_ROTATION] = this.leftLegRotation
        this.properties.metadataStorage[armorStandData.RIGHT_LEG_ROTATION] = this.rightLegRotation

        if (this.noBasePlate) this.properties.metadataStorage[armorStandData.NO_BASEPLATE] = null
        if (this.hasArms) this.properties.metadataStorage[armorStandData.HAS_ARMS] = null
        if (this.isSmall) this.properties.metadataStorage[armorStandData.IS_SMALL] = null
        if (this.isMarker) this.properties.metadataStorage[armorStandData.IS_MARKER] = null
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