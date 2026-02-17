package org.ktbridge.core.models

data class KTField(
    val name: String,
    override val type: String,
    val isNullable: Boolean,
) : KTClassifier, Field {
    override fun validate(): KTClassifier {
        return this
    }
}
