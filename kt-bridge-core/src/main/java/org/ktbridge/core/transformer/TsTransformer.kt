package org.ktbridge.core.transformer

import org.ktbridge.core.enums.KTCollectionType
import org.ktbridge.core.enums.KTypeConversion
import org.ktbridge.core.models.KTClass
import org.ktbridge.core.models.KTClassifier
import org.ktbridge.core.models.KTCollection
import org.ktbridge.core.models.KTField

class TsTransformer(val sb: StringBuilder = StringBuilder()) : Transformer {

    override fun transform(clazz: KTClass): String {
        sb.appendLine("export interface ${clazz.className} {")
        clazz.properties.forEach { transform(it) }
        return sb.appendLine("}").toString()
    }


    private fun transform(field: KTClassifier) {
        when (field) {
            is KTField -> {
                val optional = if (field.isNullable) "?" else ""
                sb.appendLine("\t${field.name}$optional: ${field.type};")
            }

            is KTCollection -> {
                when (field.type) {
                    KTCollectionType.List -> transformSet(field)
                    KTCollectionType.Set -> transformList(field)
                    KTCollectionType.Map -> transformMap(field)
                }
            }
        }
    }


    private fun transformList(field: KTCollection) {
        sb.appendLine("\t${field.name}: ${transformInnerField(field.of.first())}[];")
    }

    private fun transformSet(field: KTCollection) {
        // TODO decide if cast to Typescript SET or []
        transformList(field)
    }

    private fun transformMap(field: KTCollection) {
        val key = transformInnerField(field.of[0])
        val value = transformInnerField(field.of[1])
        sb.appendLine("\t${field.name}: { [key: $key]: $value };")
    }


    private fun transformInnerField(field: KTCollection.KTInnerField): String {
        val mappedType = KTypeConversion.toTypeScriptType(field.type)
        return if (field.isNullable) "($mappedType | null)" else mappedType
    }


}