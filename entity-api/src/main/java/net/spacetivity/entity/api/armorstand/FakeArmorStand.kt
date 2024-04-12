package net.spacetivity.entity.api.armorstand

import net.spacetivity.entity.api.utils.BaseEntity
import org.joml.Vector3f

interface FakeArmorStand : BaseEntity {

    val headRotation: Vector3f
    val bodyRotation: Vector3f

    val leftArmRotation: Vector3f
    val rightArmRotation: Vector3f

    val leftLegRotation: Vector3f
    val rightLegRotation: Vector3f

    val noBasePlate: Boolean
    val hasArms: Boolean
    val isSmall: Boolean
    val isMarker: Boolean

}