package org.ktbridge.sample.scanner.territory

import org.ktbridge.annotations.GenerateTypeScript

@GenerateTypeScript
data class CreateTerritoryDTO(
    val internalTitle: String,
)