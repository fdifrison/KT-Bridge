package org.ktbridge.core.transformer

import org.ktbridge.core.models.KTClass

interface Transformer {
    fun transform(clazz: KTClass): String
}
