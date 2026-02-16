package org.ktbridge.core.models

import org.ktbridge.core.enums.KTCollectionType
import org.ktbridge.core.enums.KTypeConversion
import kotlin.reflect.KType

data class KTCollection(
    val name: String,
    val type: KTCollectionType,
    val of: KTInnerField,
    val isNullable: Boolean,
) : KTClassifier {
    class KTInnerField(innerType: KType) {

        val type: KTypeConversion
        val isNullable: Boolean

        init {
            val kType = innerType.arguments.first().type ?: throw NoSuchElementException()
            type = KTypeConversion.valueOf(kType.toString().substringAfterLast(".").replace("?", ""))
            isNullable = kType.isMarkedNullable
        }

        override fun toString(): String {
            return "KTInnerField(type=$type, isNullable=$isNullable)"
        }

    }


}
