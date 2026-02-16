package org.ktbridge.core.models

data class KTField(
    val name: String,
    val type: String,
    val isNullable: Boolean,
) : KTClassifier {
    override fun validate(): KTClassifier {
        return this
    }
}
