package org.example.app.controllers

class CalcController {
    /*
    Class defines logic of calculating.

    For all operations:
    operands: List of numbers in double that will be used in calculation.
    Returns: IllegalArgumentException on error OR Double value(actually can be integer, check serializer module)
     */

    fun add(operands: List<Double>): Double {
        require(operands.isNotEmpty()) { "Operands list is empty" }
        return operands.sum()
    }

    fun subtract(operands: List<Double>): Double {
        require(operands.isNotEmpty()) { "Operands list is empty" }
        return operands.reduce { acc, x -> acc - x }
    }

    fun multiply(operands: List<Double>): Double {
        require(operands.isNotEmpty()) { "Operands list is empty" }
        return operands.reduce { acc, x -> acc * x }
    }

    fun divide(operands: List<Double>): Double {
        require(operands.isNotEmpty()) { "Operands list is empty" }
        return operands.reduce { acc, x ->
            if (x == 0.0) throw ArithmeticException("Divide by zero")
            acc / x
        }
    }
}