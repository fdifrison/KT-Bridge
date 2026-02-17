package org.ktbridge.core

import org.ktbridge.core.transformer.Transformer

class ClassProcessor(val converter: ClassConverter, val transformer: Transformer) {

    fun process(clazz: Class<*>): String {
        when {
            clazz.isEnum -> {
                val enum = converter.convert(clazz)
                return transformer.transform(enum)
            }

            else -> {
                val klass = converter.convert(clazz.kotlin)
                return transformer.transform(klass)
            }
        }
    }

}