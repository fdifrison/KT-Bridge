package org.ktbridge.core.models

sealed interface KTClassifier {
    fun  validate() : KTClassifier
}