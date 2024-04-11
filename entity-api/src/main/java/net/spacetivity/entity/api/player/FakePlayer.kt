package net.spacetivity.entity.api.player

import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.utils.BaseEntity

interface FakePlayer : BaseEntity {

    val profile: PlayerProfile
    val tablistRemovalTicks: Long

}