package net.spacetivity.entity.api.metadata.type

import com.comphenix.protocol.wrappers.WrappedDataWatcher.Registry
import com.comphenix.protocol.wrappers.WrappedVillagerData
import net.spacetivity.entity.api.metadata.EntityMetadata

class EntityMetadataVillager(index: Int, defaultValue: Any?) : EntityMetadata<Any>(index, Any::class.javaObjectType, defaultValue, Registry.get(WrappedVillagerData.getNmsClass())) {
}