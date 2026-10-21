package org.example.app.models

import kotlinx.serialization.Serializable

@Serializable
data class CalcRequest(val operands: List<Double>)
