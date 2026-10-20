package org.example.app.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class CalcResponse(
    val result: JsonElement? = null,
    val errors: List<String>? = null
)
