package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedChatComponent
import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataComponent(index: Int, defaultValue: WrappedChatComponent?) : EntityMetadata<WrappedChatComponent>(index, WrappedChatComponent::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.getChatComponentSerializer())