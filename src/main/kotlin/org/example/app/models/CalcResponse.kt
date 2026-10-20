package org.example.app.models

import kotlinx.serialization.Serializable
import org.example.app.serializations.DoubleAsNumberSerializer

@Serializable
data class CalcResponse(
    @Serializable(with = DoubleAsNumberSerializer::class)
    val result: Double? = null,
)
