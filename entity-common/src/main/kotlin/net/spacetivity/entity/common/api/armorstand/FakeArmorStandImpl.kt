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
    override var customName: Component,
    override var isVisible: Boolean,
    override val type: EntityType,
    override var location: Location,
    override val properties: EntityProperties,
    override var headRotation: Vector3f,
    override var bodyRotation: Vector3f,
    override var leftArmRotation: Vector3f,
    override var rightArmRotation: Vector3f,
    override var leftLegRotation: Vector3f,
    override var rightLegRotation: Vector3f,
    override var noBasePlate: Boolean,
    override var hasArms: Boolean,
    override var isSmall: Boolean,
    override var isMarker: Boolean
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

        addMetadata(armorStandData.HEAD_ROTATION, this.headRotation)
        addMetadata(armorStandData.BODY_ROTATION, this.bodyRotation)

        addMetadata(armorStandData.LEFT_ARM_ROTATION, this.leftArmRotation)
        addMetadata(armorStandData.RIGHT_ARM_ROTATION, this.rightArmRotation)

        addMetadata(armorStandData.LEFT_LEG_ROTATION, this.leftLegRotation)
        addMetadata(armorStandData.RIGHT_LEG_ROTATION, this.rightLegRotation)

        if (this.noBasePlate) addMetadata(armorStandData.NO_BASEPLATE, null)
        if (this.hasArms) addMetadata(armorStandData.HAS_ARMS, null)
        if (this.isSmall) addMetadata(armorStandData.IS_SMALL, null)
        if (this.isMarker) addMetadata(armorStandData.IS_MARKER, null)
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