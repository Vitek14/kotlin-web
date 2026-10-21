package org.example.app.servlets

class SubtractServlet : BaseCalcServlet() {
    override fun calculate(operands: List<Double>) = calculator.subtract(operands)
}
