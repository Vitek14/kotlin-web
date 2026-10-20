package org.example.app.extensions

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive

// serializes double with a number without a remainder to integer(long):
fun Double.toJsonNumber(): JsonElement =
    if (this % 1.0 == 0.0) JsonPrimitive(toLong())
    else JsonPrimitive(this)
