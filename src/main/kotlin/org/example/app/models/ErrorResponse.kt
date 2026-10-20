package org.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val errorCode: String,
    val errorMessage: String,
)
