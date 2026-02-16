package org.ktbridge.core

import org.ktbridge.core.transformer.Transformer

class ClassProcessor(val converter: ClassConverter, val transformer: Transformer) {

    fun process(clazz: Class<*>): String {
        val kTClass = converter.convert(clazz.kotlin)
        return transformer.transform(kTClass)
    }

}