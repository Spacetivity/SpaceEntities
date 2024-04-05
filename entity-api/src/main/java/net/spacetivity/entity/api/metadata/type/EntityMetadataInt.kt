package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataInt(index: Int, defaultValue: Int?) : EntityMetadata<Int>(index, Int::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.get(Int::class.javaObjectType))