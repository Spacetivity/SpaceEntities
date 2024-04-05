package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher
import net.spacetivity.entity.api.metadata.EntityMetadata
import org.joml.Vector3f

class EntityMetadataVector3f(index: Int, defaultValue: Vector3f?) : EntityMetadata<Vector3f>(index, Vector3f::class.javaObjectType, defaultValue, WrappedDataWatcher.Registry.get(Vector3f::class.javaObjectType))