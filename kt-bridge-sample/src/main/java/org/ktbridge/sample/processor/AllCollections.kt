package org.ktbridge.sample.processor

import org.ktbridge.annotations.GenerateTypeScript

@GenerateTypeScript
data class AllCollections(
    val listOfCustom : List<User>,
    val listProp: List<String>,
    val setProp: Set<Int>,
    val mapProp: Map<String, Double>,
//    val nestedListProp: List<List<String>>, //  TODO support nested collections
    val listWithNullableElementsProp: List<String?>,
    val nullableListProp: List<String>?
) {
    class User
}