package org.ktbridge.core.models

import org.ktbridge.core.enums.KTCollectionType
import kotlin.reflect.KType
import kotlin.reflect.KTypeProjection

data class KTCollection(
    val name: String,
    val type: KTCollectionType,
    val of: List<KTInnerField> = emptyList(),
    val isNullable: Boolean,
) : KTClassifier {

    override fun validate(): KTClassifier {
        when (type) {
            KTCollectionType.List, KTCollectionType.Set -> require(of.size == 1)
            KTCollectionType.Map -> require(of.size == 2)
        }
        return this
    }

    class KTInnerField(innerType: KTypeProjection) {

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
