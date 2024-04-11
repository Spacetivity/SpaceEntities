package net.spacetivity.entity.common

import com.comphenix.protocol.ProtocolManager
import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.EntityApi
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.api.player.FakePlayerBuilder
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.common.api.entity.RawFakeEntityBuilderImpl
import net.spacetivity.entity.common.api.player.FakePlayerBuilderImpl
import net.spacetivity.entity.common.api.properties.EntityPropertiesBuilderImpl
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin

class EntityApiImpl(override val plugin: JavaPlugin, override val protocolManager: ProtocolManager) : EntityApi {

    override fun newFakeEntity(key: String, type: EntityType, location: Location): RawFakeEntityBuilder {
        return RawFakeEntityBuilderImpl(key, location, type)
    }

    override fun newFakePlayer(key: String, location: Location, profile: PlayerProfile): FakePlayerBuilder {
        return FakePlayerBuilderImpl(key, location, profile)
    }

    override fun newEntityProperties(): EntityPropertiesBuilder {
        return EntityPropertiesBuilderImpl()
    }

}