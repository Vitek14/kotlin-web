package org.example.app.serializations
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.nullable
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder


// some methods like encodeNull or decodeNull are experimental and requires 'confirmation' that we want to use them,
// so we're using optin annotation
@OptIn(ExperimentalSerializationApi::class)
object DoubleAsNumberSerializer : KSerializer<Double?> {
    /*
    Custom serialization class to process double.
    When double without remainder(example 1.0), it converts to long
     */

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("org.example.DoubleAsNumber", PrimitiveKind.DOUBLE).nullable

    override fun serialize(encoder: Encoder, value: Double?) {
        if (value == null) {
            encoder.encodeNull()
            return
        }
        if (value % 1.0 == 0.0) encoder.encodeLong(value.toLong())
        else encoder.encodeDouble(value)
    }

    override fun deserialize(decoder: Decoder): Double? {
        if (!decoder.decodeNotNullMark()) {
            decoder.decodeNull()
            return null
        }
        return decoder.decodeDouble()
    }
}
