package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataFloat(index: Int, defaultValue: Float?) : EntityMetadata<Float>(index, Float::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.get(Float::class.javaObjectType))