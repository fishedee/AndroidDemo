package com.example.myapplication

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.isAccessible

class KotlinAdapterFactory : TypeAdapterFactory {

    private fun Class<*>.isKotlinClass(): Boolean {
        return this.declaredAnnotations.any {
            // 只关心 kt 类型
            it.annotationClass.qualifiedName == "kotlin.Metadata"
        }
    }

    override fun <T : Any> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T>? {
        return if (type.rawType.isKotlinClass()) {
            val kClass = (type.rawType as Class<*>).kotlin
            val delegateAdapter = gson.getDelegateAdapter(this, type)
            KotlinAdapter<T>(delegateAdapter, kClass as KClass<T>)
        } else {
            null
        }
    }
}

class KotlinAdapter<T : Any>(
    private val delegateAdapter: TypeAdapter<T>,
    private val kClass: KClass<T>
) : TypeAdapter<T>() {

    override fun read(input: JsonReader?): T? {
        return delegateAdapter.read(input)?.apply {
            nullCheck(this)
        }
    }

    override fun write(out: JsonWriter?, value: T) {
        delegateAdapter.write(out, value)
    }

    private fun nullCheck(value: T) {
        kClass.declaredMemberProperties.forEach { prop ->
            prop.isAccessible = true
            if (!prop.returnType.isMarkedNullable && prop(value) == null)
                throw JsonParseException(
                    "Field: '${prop.name}' in Class '${kClass.java.name}' is marked nonnull but found null value"
                )
        }
    }
}