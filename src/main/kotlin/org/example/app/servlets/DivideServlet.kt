package org.example.app.servlets

class DivideServlet : BaseCalcServlet() {
    override fun calculate(operands: List<Double>) = calculator.divide(operands)
}
