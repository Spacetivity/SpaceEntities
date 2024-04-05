package net.spacetivity.entity.api

import com.comphenix.protocol.ProtocolManager
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.api.player.FakePlayerBuilder
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.concurrent.ThreadLocalRandom

interface EntityApi {

    val protocolManager: ProtocolManager

    fun randomEntityId(): Int = ThreadLocalRandom.current().nextInt(100000) + 10000

    fun newFakeEntity(key: String, type: EntityType, location: Location): RawFakeEntityBuilder
    fun newFakePlayer(key: String, location: Location): FakePlayerBuilder

}