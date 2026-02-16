package org.ktbridge.sample.processor

import org.ktbridge.annotations.GenerateTypeScript
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

@GenerateTypeScript
data class AllNonNullTypes(
    val intProp: Int,
    val doubleProp: Double,
    val bigDecimalProp: BigDecimal,
    val booleanProp: Boolean,
    val stringProp: String,
    val uuidProp: UUID,
    val localDateProp: LocalDate,
    val anyProp: Any
)

