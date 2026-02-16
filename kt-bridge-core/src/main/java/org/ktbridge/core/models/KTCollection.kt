package org.ktbridge.core.models

import org.ktbridge.core.enums.KTCollectionType
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

data class KTCollection(
    val name: String,
    val type: KTCollectionType,
    val of: List<KTClassifier> = emptyList(),
    val isNullable: Boolean,
) : KTClassifier {

    class KTInnerField(innerType: KTypeProjection) : KTClassifier {

        val type: String
        val isNullable: Boolean

        init {
            val kType = innerType.type ?: throw NoSuchElementException()
            type = getType(kType)
            isNullable = kType.isMarkedNullable
        }

        private fun getType(kType: KType) = kType.toString()
            .substringAfterLast(".") // take last part kotlin.lang.String
            .replace("?", "") // remove nullability marker "?"

        override fun toString(): String {
            return "KTInnerField(type=$type, isNullable=$isNullable)"
        }

    }


}
