package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataCustomName : EntityMetadata<WrappedChatComponent>(2, WrappedChatComponent::class.javaObjectType, null, Registry.getChatComponentSerializer(true))