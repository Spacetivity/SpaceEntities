package net.spacetivity.entity.api.player

import com.destroystokyo.paper.profile.ProfileProperty
import net.spacetivity.entity.api.utils.BaseEntity
import net.spacetivity.entity.api.utils.Equipable
import net.spacetivity.entity.api.utils.Rotatable

interface FakePlayer : BaseEntity, Equipable, Rotatable {

    val property: ProfileProperty
    val tablistRemovalTicks: Long

}