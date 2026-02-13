package org.ktbridge.core

import org.ktbridge.core.models.KClazz
import org.ktbridge.core.models.KFieldz
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class ClassProcessor {

    private fun Class<*>.asKotlinClass(): KClass<out Any> = this.kotlin

    private fun KProperty1<out Any, *>.convert(): KFieldz {
        return KFieldz(
            name = this.name,
            type = this.returnType.classifier.toString().substringAfterLast("."),
            isNullable = this.returnType.isMarkedNullable,
        )
    }

    private fun KClass<*>.convert(): KClazz {
        return KClazz(
            className = this.simpleName ?: this.qualifiedName ?: this.java.simpleName,
            properties = this.memberProperties.map { it.convert() },
        )
    }

    fun process(clazz: Class<*>): KClazz {
       return clazz.asKotlinClass().convert()
    }

}