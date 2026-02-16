package org.ktbridge.sample.processor

import org.ktbridge.annotations.GenerateTypeScript
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

@GenerateTypeScript
data class AllNullableTypes(
    val intProp: Int?,
    val doubleProp: Double?,
    val bigDecimalProp: BigDecimal?,
    val booleanProp: Boolean?,
    val stringProp: String?,
    val listProp: List<String>?,
    val listNullableProp: List<String?>,
    val uuidProp: UUID?,
    val nullableLocalDateProp: LocalDate?,
    val anyProp: Any?,
    val customProp: User?,
){
    class User
}


