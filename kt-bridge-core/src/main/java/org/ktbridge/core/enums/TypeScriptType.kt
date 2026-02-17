package org.ktbridge.core.enums

enum class TypeScriptType {
    number,
    string,
    boolean,
    any;

    companion object {
        fun isBaseType(tsType: String): Boolean {
            return TypeScriptType.entries.any { it.name.equals(tsType, ignoreCase = true) }
        }
    }


}