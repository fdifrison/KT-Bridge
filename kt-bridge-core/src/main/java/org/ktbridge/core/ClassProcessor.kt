package org.ktbridge.core

import org.ktbridge.core.models.KTClass

class ClassProcessor(val converter: ClassConverter) {

    fun process(clazz: Class<*>): KTClass {
        return converter.convert(clazz.kotlin)
    }

}