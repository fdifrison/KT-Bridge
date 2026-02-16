package org.ktbridge.core

import org.ktbridge.core.enums.KTCollectionType
import org.ktbridge.core.enums.KTypeConversion
import org.ktbridge.core.models.KTClass
import org.ktbridge.core.models.KTClassifier
import org.ktbridge.core.models.KTCollection
import org.ktbridge.core.models.KTField
import org.ktbridge.core.utils.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties


class ClassConverter {

    fun convert(kClass: KClass<out Any>): KTClass = KTClass(
        className = kClass.simpleName ?: kClass.qualifiedName ?: kClass.java.simpleName,
        properties = kClass.memberProperties.map { kPropConverter(it) }
    )

    private fun kPropConverter(kProp: KProperty1<out Any, *>): KTClassifier = when {
        kProp.isList(kProp.isNullable()) -> kPropToKTCollection(kProp)
        kProp.isMap(kProp.isNullable()) -> kPropToKTCollection(kProp)
        kProp.isSet(kProp.isNullable()) -> kPropToKTCollection(kProp)
        else -> kPropToKTField(kProp)
    }

    private fun kPropToKTCollection(kProp: KProperty1<out Any, *>): KTCollection = KTCollection(
        name = kProp.name,
        type = KTCollectionType.valueOf(kProp.type()),
        of = kProp.returnType.arguments.map { KTCollection.KTInnerField(innerType = it) },
        isNullable = kProp.isNullable(),
    )


    private fun kPropToKTField(kProp: KProperty1<out Any, *>): KTField = KTField(
        name = kProp.name,
        type = KTypeConversion.valueOf(kProp.type()),
        isNullable = kProp.isNullable(),
    )

}




