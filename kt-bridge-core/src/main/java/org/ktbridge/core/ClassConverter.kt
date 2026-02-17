package org.ktbridge.core

import org.ktbridge.core.enums.KTCollectionType
import org.ktbridge.core.enums.KTypeConversion
import org.ktbridge.core.models.*
import org.ktbridge.core.utils.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties


class ClassConverter {

    fun convert(kClass: KClass<out Any>): KTClass = KTClass(
        className = kClass.simpleName ?: kClass.qualifiedName ?: kClass.java.simpleName,
        properties = kClass.memberProperties.map { kPropConverter(it) }
    )

    fun convert(clazz: Class<*>): KTEnum = KTEnum(
        className = clazz.simpleName,
        properties = clazz.enumConstants.map { it.toString() }
    )

    private fun kPropConverter(kProp: KProperty1<out Any, *>): KTClassifier = when {
        kProp.isList(kProp.isNullable()) -> kPropToKTCollection(kProp)
        kProp.isMap(kProp.isNullable()) -> kPropToKTCollection(kProp)
        kProp.isSet(kProp.isNullable()) -> kPropToKTCollection(kProp)
        else -> kPropToKTField(kProp)
    }

    private fun kPropToKTCollection(kProp: KProperty1<out Any, *>): KTClassifier = KTCollection(
        name = kProp.name,
        type = KTCollectionType.valueOf(kProp.type()),
        of = kProp.returnType.arguments.map { KTCollection.KTInnerField(innerType = it) }, // handle arguments > 1 for Maps
        isNullable = kProp.isNullable(),
    ).validate()


    private fun kPropToKTField(kProp: KProperty1<out Any, *>): KTClassifier = KTField(
        name = kProp.name,
        type = KTypeConversion.toTypeScriptType(kProp.type()),
        isNullable = kProp.isNullable(),
    ).validate()

}




