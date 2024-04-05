package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataBoolean(index: Int, defaultValue: Boolean?) : EntityMetadata<Boolean>(index, Boolean::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.get(Boolean::class.javaObjectType))