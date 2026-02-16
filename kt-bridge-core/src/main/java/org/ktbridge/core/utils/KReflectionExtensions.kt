package org.ktbridge.core.utils

import kotlin.reflect.KProperty1
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.full.withNullability

fun KProperty1<out Any, *>.isList(nullable: Boolean): Boolean =
    this.returnType.isSubtypeOf(List::class.starProjectedType.withNullability(nullable))

fun KProperty1<out Any, *>.isMap(nullable: Boolean): Boolean =
    this.returnType.isSubtypeOf(Map::class.starProjectedType.withNullability(nullable))

fun KProperty1<out Any, *>.isSet(nullable: Boolean): Boolean =
    this.returnType.isSubtypeOf(Set::class.starProjectedType.withNullability(nullable))

fun KProperty1<out Any, *>.type(): String = this.returnType.classifier.toString().substringAfterLast(".").substringAfterLast("$")
fun KProperty1<out Any, *>.isNullable(): Boolean = this.returnType.isMarkedNullable