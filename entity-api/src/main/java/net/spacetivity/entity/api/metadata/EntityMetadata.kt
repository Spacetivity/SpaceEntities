package net.spacetivity.entity.api.metadata

import com.comphenix.protocol.wrappers.WrappedDataWatcher.Serializer

open class EntityMetadata<T>(val index: Int, val clazz: Class<T>, val defaultValue: T?, val serializer: Serializer)