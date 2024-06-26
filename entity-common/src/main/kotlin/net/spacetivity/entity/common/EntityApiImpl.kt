package net.spacetivity.entity.common

import com.comphenix.protocol.ProtocolManager
import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.EntityApi
import net.spacetivity.entity.api.armorstand.FakeArmorStandBuilder
import net.spacetivity.entity.api.display.FakeTextDisplayBuilder
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.api.player.FakePlayerBuilder
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import net.spacetivity.entity.api.utils.BaseEntity
import net.spacetivity.entity.common.api.armorstand.FakeArmorStandBuilderImpl
import net.spacetivity.entity.common.api.display.FakeTextDisplayBuilderImpl
import net.spacetivity.entity.common.api.entity.RawFakeEntityBuilderImpl
import net.spacetivity.entity.common.api.player.FakePlayerBuilderImpl
import net.spacetivity.entity.common.api.properties.EntityPropertiesBuilderImpl
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class EntityApiImpl(override val plugin: JavaPlugin, override val protocolManager: ProtocolManager) : EntityApi {

    override fun setEntityMetadata(entity: BaseEntity, uuid: UUID) {
        EntityUtils.setMetadata(entity, uuid)
    }

    override fun fakeEntity(key: String, type: EntityType, location: Location): RawFakeEntityBuilder {
        return RawFakeEntityBuilderImpl(key, location, type)
    }

    override fun fakeArmorStand(key: String, location: Location): FakeArmorStandBuilder {
        return FakeArmorStandBuilderImpl(key, location)
    }

    override fun fakeTextDisplay(key: String, location: Location): FakeTextDisplayBuilder {
        return FakeTextDisplayBuilderImpl(key, location)
    }

    override fun fakePlayer(key: String, location: Location, profile: PlayerProfile): FakePlayerBuilder {
        return FakePlayerBuilderImpl(key, location, profile)
    }

    override fun newEntityProperties(): EntityPropertiesBuilder {
        return EntityPropertiesBuilderImpl()
    }

}