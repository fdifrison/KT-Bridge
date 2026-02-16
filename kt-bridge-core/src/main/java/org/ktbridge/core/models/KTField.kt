package org.ktbridge.core.models

import org.ktbridge.core.enums.KTypeConversion

data class KTField(
    val name: String,
    val type: KTypeConversion,
    val isNullable: Boolean,
) : KTClassifier
