package org.ktbridge.core.models

data class KTClass(
    val className: String,
    val properties: List<KTClassifier>,
)
