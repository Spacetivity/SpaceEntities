package net.spacetivity.entity.api

import com.comphenix.protocol.ProtocolManager
import com.destroystokyo.paper.profile.PlayerProfile
import net.spacetivity.entity.api.armorstand.FakeArmorStandBuilder
import net.spacetivity.entity.api.entity.RawFakeEntityBuilder
import net.spacetivity.entity.api.player.FakePlayerBuilder
import net.spacetivity.entity.api.properties.EntityPropertiesBuilder
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.ThreadLocalRandom

interface EntityApi {

    val plugin: JavaPlugin
    val protocolManager: ProtocolManager

    fun randomEntityId(): Int = ThreadLocalRandom.current().nextInt(100000) + 10000

    fun fakeEntity(key: String, type: EntityType, location: Location): RawFakeEntityBuilder
    fun fakeArmorStand(key: String, location: Location): FakeArmorStandBuilder
    fun fakePlayer(key: String, location: Location, profile: PlayerProfile): FakePlayerBuilder

    fun newEntityProperties(): EntityPropertiesBuilder

}