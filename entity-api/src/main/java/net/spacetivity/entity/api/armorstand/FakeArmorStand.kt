package net.spacetivity.entity.api.armorstand

import net.spacetivity.entity.api.utils.BaseEntity
import org.joml.Vector3f

interface FakeArmorStand : BaseEntity {

    var headRotation: Vector3f
    var bodyRotation: Vector3f

    var leftArmRotation: Vector3f
    var rightArmRotation: Vector3f

    var leftLegRotation: Vector3f
    var rightLegRotation: Vector3f

    var noBasePlate: Boolean
    var hasArms: Boolean
    var isSmall: Boolean
    var isMarker: Boolean

}