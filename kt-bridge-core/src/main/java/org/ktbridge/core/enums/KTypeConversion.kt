package org.ktbridge.core.enums

enum class KTypeConversion(val typeScriptType: TypeScriptType) {
    Int(TypeScriptType.number),
    Long(TypeScriptType.number),
    Double(TypeScriptType.number),
    Float(TypeScriptType.number),
    BigDecimal(TypeScriptType.number),
    String(TypeScriptType.string),
    Boolean(TypeScriptType.boolean),
    Any(TypeScriptType.any),
    UUID(TypeScriptType.string),
    LocalDate(TypeScriptType.string);

    companion object {
        fun toTypeScriptType(type: String) = KTypeConversion.entries
            .find { it.name == type }
            ?.typeScriptType
            ?.name
            ?: type
    }
}

