package net.spacetivity.entity.common.api.armorstand

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.armorstand.FakeArmorStand
import net.spacetivity.entity.api.armorstand.FakeArmorStandBuilder
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.joml.Vector3f

class FakeArmorStandBuilderImpl(private val key: String, private val location: Location) : FakeArmorStandBuilder {

    private var isCustomNameShown: Boolean = false
    private var customName: Component = Component.empty()
    private var isVisible: Boolean = true

    private var headRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)
    private var bodyRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)

    private var leftArmRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)
    private var rightArmRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)

    private var leftLegRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)
    private var rightLegRotation: Vector3f = Vector3f(0.0F, 0.0F, 0.0F)

    private var noBasePlate: Boolean = false
    private var hasArms: Boolean = false
    private var isSmall: Boolean = false
    private var isMarker: Boolean = false

    private var properties = EntityProperties(mutableMapOf(), false, 5.0, mutableMapOf(), null)

    override fun customNameShown(isCustomNameShown: Boolean): FakeArmorStandBuilder {
        this.isCustomNameShown = isCustomNameShown
        return this
    }

    override fun customName(customName: Component): FakeArmorStandBuilder {
        this.customName = customName
        return this
    }

    override fun visible(isVisible: Boolean): FakeArmorStandBuilder {
        this.isVisible = isVisible
        return this
    }

    override fun headRotation(headRotation: Vector3f): FakeArmorStandBuilder {
        this.headRotation = headRotation
        return this
    }

    override fun bodyRotation(bodyRotation: Vector3f): FakeArmorStandBuilder {
        this.bodyRotation = bodyRotation
        return this
    }

    override fun leftArmRotation(leftArmRotation: Vector3f): FakeArmorStandBuilder {
        this.leftArmRotation = leftArmRotation
        return this
    }

    override fun rightArmRotation(rightArmRotation: Vector3f): FakeArmorStandBuilder {
        this.rightArmRotation = rightArmRotation
        return this
    }

    override fun leftLegRotation(leftLegRotation: Vector3f): FakeArmorStandBuilder {
        this.leftLegRotation = leftLegRotation
        return this
    }

    override fun rightLegRotation(rightLegRotation: Vector3f): FakeArmorStandBuilder {
        this.rightLegRotation = rightLegRotation
        return this
    }

    override fun noBasePlate(): FakeArmorStandBuilder {
        this.noBasePlate = true
        return this
    }

    override fun enableArms(): FakeArmorStandBuilder {
        this.hasArms = true
        return this
    }

    override fun setSmall(): FakeArmorStandBuilder {
        this.isSmall = true
        return this
    }

    override fun setMarker(): FakeArmorStandBuilder {
        this.isMarker = true
        return this
    }

    override fun properties(propertiesBuilder: EntityPropertiesBuilder): FakeArmorStandBuilder {
        this.properties = propertiesBuilder.build()
        return this
    }

    override fun build(): FakeArmorStand {
        return FakeArmorStandImpl(
            this.key,
            this.isCustomNameShown,
            this.customName,
            this.isVisible,
            EntityType.ARMOR_STAND,
            this.location,
            this.properties,
            this.headRotation,
            this.bodyRotation,
            this.leftArmRotation,
            this.rightArmRotation,
            this.leftLegRotation,
            this.rightLegRotation,
            this.noBasePlate,
            this.hasArms,
            this.isSmall,
            this.isMarker
        )
    }

}