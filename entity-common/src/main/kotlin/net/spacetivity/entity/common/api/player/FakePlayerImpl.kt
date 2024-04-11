package net.spacetivity.entity.common.api.player

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import com.comphenix.protocol.wrappers.WrappedGameProfile
import com.comphenix.protocol.wrappers.WrappedSignedProperty
import com.destroystokyo.paper.profile.PlayerProfile
import com.destroystokyo.paper.profile.ProfileProperty
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import net.spacetivity.entity.api.EntityProvider
import net.spacetivity.entity.api.player.FakePlayer
import net.spacetivity.entity.api.properties.EntityProperties
import net.spacetivity.entity.common.utils.EntityUtils
import org.bukkit.Location
import org.bukkit.entity.EntityType
import java.util.*

class FakePlayerImpl(
    override val key: String,
    override val tablistRemovalTicks: Long,
    override val profile: PlayerProfile,
    override val isVisible: Boolean,
    override val type: EntityType,
    override val location: Location,
    override val properties: EntityProperties
) : FakePlayer {

    override val uuid: UUID = UUID.randomUUID()
    override val entityId: Int = EntityProvider.api.randomEntityId()
    override val isCustomNameShown: Boolean = false
    override val customName: Component = Component.text("npc-" + this.uuid.toString().split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]);
    override val dataWatcher: WrappedDataWatcher = WrappedDataWatcher()

    override val viewers: MutableSet<UUID> = mutableSetOf()

    val gameProfile: WrappedGameProfile = WrappedGameProfile(this.uuid, PlainTextComponentSerializer.plainText().serialize(this.customName))

    init {
        this.gameProfile.properties.removeAll("textures")

        val property: ProfileProperty = this.profile.properties.first()
        this.gameProfile.properties.put("textures", WrappedSignedProperty("textures", property.value, property.signature))
    }

    override fun spawn(viewer: UUID) {
        EntityUtils.spawn(this, viewer)
    }

    override fun despawn(viewer: UUID) {

    }

    override fun teleport(location: Location) {

    }

}