package org.ktbridge.core.transformer

import org.ktbridge.core.models.KTClass
import org.ktbridge.core.models.KTEnum

interface Transformer {
    fun transform(clazz: KTClass): String
    fun transform(clazz: KTEnum): String
}
