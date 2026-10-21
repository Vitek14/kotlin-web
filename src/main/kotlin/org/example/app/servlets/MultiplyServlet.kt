package org.example.app.servlets

class MultiplyServlet : BaseCalcServlet() {
    override fun calculate(operands: List<Double>) = calculator.multiply(operands)
}
