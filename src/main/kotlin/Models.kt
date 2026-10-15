package org.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

@Serializable
data class CalcRequest(val operands: List<Double>)

@Serializable
data class CalcResponse(
    val result: JsonElement? = null,
    val errors: List<String>? = null
)

val json = Json {
    /*
    output will be only results or errors.
     */
    explicitNulls = false
    encodeDefaults = false
}

// serializes double with a number without a remainder to integer(long):
fun Double.toJsonNumber(): JsonElement =
    if (isFinite() && this == toLong().toDouble()) JsonPrimitive(toLong())
    else JsonPrimitive(this)
