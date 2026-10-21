package org.example.app.servlets

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.serialization.SerializationException
import org.example.app.models.CalcRequest
import org.example.app.models.CalcResponse
import org.example.app.controllers.CalcController
import org.example.app.models.ErrorResponse
import org.example.app.serializations.json

abstract class BaseCalcServlet : HttpServlet() {
    /*
    Class only accepts requests and serializes data from them using json parser library
    Logic of calculating defines in Calculator class.
     */

    protected val calculator = CalcController()

    protected abstract fun calculate(operands: List<Double>): Double

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        resp.contentType = "application/json; charset=UTF-8"

        val body = req.inputStream.readBytes().toString(Charsets.UTF_8)  // by default it uses ISO-8859-1

        try {
            val request = json.decodeFromString<CalcRequest>(body)
            val result = calculate(request.operands)
            resp.writer.write(json.encodeToString(CalcResponse(result)))
        }
        catch (e: SerializationException) {
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write(
                json.encodeToString(ErrorResponse(
                    "INVALID_REQUEST",
                    "Invalid request format: ${e.message}"
                    )
                )
            )
        }
        catch (e: ArithmeticException) {
            resp.status = HttpServletResponse.SC_BAD_REQUEST
            resp.writer.write(
                json.encodeToString(
                    ErrorResponse("MATH_ERROR", "Math error: ${e.message}"))
                )
        }
        catch (e: Exception) {
            resp.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            val message = e.message ?: e::class.simpleName ?: "Internal Server Error"
            resp.writer.write(json.encodeToString(ErrorResponse("INTERNAL_ERROR", message)))
        }
    }
}
