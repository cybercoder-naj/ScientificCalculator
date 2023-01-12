package com.github.cybercodernaj.calculator.parser

import com.github.cybercodernaj.calculator.operations.BinaryOperator
import com.github.cybercodernaj.calculator.operations.UnaryOperator

internal sealed class Token {
    fun toBinaryOperator(): BinaryOperator {
        return when (this) {
            Div -> BinaryOperator.Div
            Mul -> BinaryOperator.Mul
            Add -> BinaryOperator.Add
            Sub -> BinaryOperator.Sub
            else -> throw Exception()
        }
    }

    fun toUnaryOperator(): UnaryOperator {
        return when (this) {
            Add -> UnaryOperator.Pos
            Sub -> UnaryOperator.Neg
            else -> throw Exception()
        }
    }

    data class Num(val number: Double) : Token()

    object LParen : Token()

    object RParen : Token()

    object Div : Token()

    object Mul : Token()

    object Add : Token()

    object Sub : Token()

    object EOL : Token()
}
