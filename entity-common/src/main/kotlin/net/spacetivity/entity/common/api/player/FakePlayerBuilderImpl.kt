package net.spacetivity.entity.common.api.player

import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.player.FakePlayer
import net.spacetivity.entity.api.player.FakePlayerBuilder
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import org.bukkit.Location
import org.bukkit.entity.EntityType

class FakePlayerBuilderImpl(private val key: String, private val location: Location, private var profile: PlayerProfile) : FakePlayerBuilder {

    private var isVisible: Boolean = true
    private var tablistRemovalTicks: Long = 20

    private var properties = EntityProperties(mutableMapOf(), false, 5.0, mutableMapOf())

    override fun visible(isVisible: Boolean): FakePlayerBuilder {
        this.isVisible = isVisible
        return this
    }

    override fun tablistRemovalTicks(ticks: Long): FakePlayerBuilder {
        this.tablistRemovalTicks = ticks
        return this
    }

    override fun profile(profile: PlayerProfile): FakePlayerBuilder {
        this.profile = profile
        return this
    }

    override fun properties(propertiesBuilder: EntityPropertiesBuilder): FakePlayerBuilder {
        this.properties = propertiesBuilder.build()
        return this
    }

    override fun build(): FakePlayer {
        return FakePlayerImpl(this.key, this.tablistRemovalTicks, this.profile, this.isVisible, EntityType.PLAYER , this.location, this.properties)
    }

}