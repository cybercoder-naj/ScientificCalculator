package com.github.cybercodernaj.calculator.operations

import kotlin.math.pow

internal sealed class BinaryOperator {
    object Exp : BinaryOperator()
    object Div : BinaryOperator()
    object Mul : BinaryOperator()
    object Add : BinaryOperator()
    object Sub : BinaryOperator()

    operator fun invoke(firstValue: Double, secondValue: Double): Double {
        return when(this) {
            Exp -> firstValue.pow(secondValue)
            Div -> firstValue / secondValue
            Mul -> firstValue * secondValue
            Add -> firstValue + secondValue
            Sub -> firstValue - secondValue
        }
    }
}