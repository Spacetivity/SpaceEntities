package net.spacetivity.entity.common

import com.comphenix.protocol.ProtocolManager
import net.spacetivity.entity.api.EntityApi
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.common.api.entity.RawFakeEntityBuilderImpl
import org.bukkit.Location
import org.bukkit.entity.EntityType

class EntityApiImpl(override val protocolManager: ProtocolManager) : EntityApi {

    override fun newFakeEntity(key: String, type: EntityType, location: Location): RawFakeEntityBuilder {
        return RawFakeEntityBuilderImpl(key, location, type)
    }

}