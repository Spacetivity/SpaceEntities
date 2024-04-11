package net.spacetivity.entity.api.player

import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.api.utils.BaseEntityBuilder

interface FakePlayerBuilder : BaseEntityBuilder<FakePlayer> {

    fun visible(isVisible: Boolean): FakePlayerBuilder

    fun tablistRemovalTicks(ticks: Long): FakePlayerBuilder

    fun profile(profile: PlayerProfile): FakePlayerBuilder

    fun properties(propertiesBuilder: EntityPropertiesBuilder): FakePlayerBuilder

}