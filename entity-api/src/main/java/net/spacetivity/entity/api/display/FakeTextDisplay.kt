package net.spacetivity.entity.api.display

import net.kyori.adventure.text.Component
import net.spacetivity.entity.api.utils.BaseEntity
import org.joml.Vector3f

interface FakeTextDisplay : BaseEntity {

    var billboardConstraints: Boolean
    var translation: Vector3f
    var scale: Vector3f
    var width: Float
    var height: Float
    var backgroundColor: Int
    var textOpacity: Byte
    var hasShadow: Boolean
    var isSeeThrough: Boolean
    var text: Component

    override var customName: Component
        get() = text
        set(value) {
            this.text = value
        }

}