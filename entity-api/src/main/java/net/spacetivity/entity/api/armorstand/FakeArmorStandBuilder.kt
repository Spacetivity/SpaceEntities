package net.spacetivity.entity.api.armorstand

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.api.utils.BaseEntityBuilder
import org.joml.Vector3f

interface FakeArmorStandBuilder : BaseEntityBuilder<FakeArmorStand> {

    fun customNameShown(isCustomNameShown: Boolean): FakeArmorStandBuilder
    fun customName(customName: Component): FakeArmorStandBuilder

    fun visible(isVisible: Boolean): FakeArmorStandBuilder

    fun headRotation(headRotation: Vector3f): FakeArmorStandBuilder
    fun bodyRotation(bodyRotation: Vector3f): FakeArmorStandBuilder

    fun leftArmRotation(leftArmRotation: Vector3f): FakeArmorStandBuilder
    fun rightArmRotation(rightArmRotation: Vector3f): FakeArmorStandBuilder

    fun leftLegRotation(leftLegRotation: Vector3f): FakeArmorStandBuilder
    fun rightLegRotation(rightLegRotation: Vector3f): FakeArmorStandBuilder

    fun noBasePlate(): FakeArmorStandBuilder
    fun enableArms(): FakeArmorStandBuilder
    fun setSmall(): FakeArmorStandBuilder
    fun setMarker(): FakeArmorStandBuilder

    fun properties(propertiesBuilder: EntityPropertiesBuilder): FakeArmorStandBuilder

}