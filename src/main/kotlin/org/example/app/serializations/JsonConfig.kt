package org.example.app.serializations

import kotlinx.serialization.json.Json

val json = Json {
    /*
    output will be only results or errors.
     */
    explicitNulls = false
    encodeDefaults = false
}
