package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataByte(index: Int, defaultValue: Byte?) : EntityMetadata<Byte>(index, Byte::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.get(Byte::class.javaObjectType))