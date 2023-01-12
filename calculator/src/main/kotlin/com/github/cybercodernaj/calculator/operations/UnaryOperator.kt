package com.github.cybercodernaj.calculator.operations

import kotlin.math.*

internal sealed class UnaryOperator {
    object Pos : UnaryOperator()

    object Neg : UnaryOperator()

    object Sin : UnaryOperator()

    object Cos : UnaryOperator()

    object Tan : UnaryOperator()

    object Cot : UnaryOperator()

    object Sec : UnaryOperator()

    object Csc : UnaryOperator()

    object Log : UnaryOperator()

    object Abs : UnaryOperator()


    operator fun invoke(value: Double): Double {
        return when(this) {
            Pos -> value
            Neg -> -value
            Sin -> sin(value)
            Cos -> cos(value)
            Tan -> tan(value)
            Cot -> 1.0 / tan(value)
            Sec -> 1.0 / cos(value)
            Csc -> 1.0 / sin(value)
            Log -> log(value, Math.E)
            Abs -> abs(value)
        }
    }
}
