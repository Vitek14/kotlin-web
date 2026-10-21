package org.example.app.servlets

class AddServlet : BaseCalcServlet() {
    override fun calculate(operands: List<Double>) = calculator.add(operands)
}
