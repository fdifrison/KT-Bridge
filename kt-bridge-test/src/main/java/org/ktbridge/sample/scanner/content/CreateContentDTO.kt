package org.ktbridge.sample.scanner.content

import org.ktbridge.annotations.GenerateTypeScript

@GenerateTypeScript
data class CreateContentDTO(
    var parentId: String? = null,
    val type: ContentType,
    val internalTitle: String,
    val taxonomies: List<String>? = null
)

enum class ContentType {
    SIMPLE,
    EVENT,
}