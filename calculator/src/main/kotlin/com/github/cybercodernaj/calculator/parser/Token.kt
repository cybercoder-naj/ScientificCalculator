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
            is Var -> {
               val (name) = this
               when (name) {
                   "sin" -> UnaryOperator.Sin
                   "cos" -> UnaryOperator.Cos
                   "tan" -> UnaryOperator.Tan
                   "cot" -> UnaryOperator.Cot
                   "sec" -> UnaryOperator.Sec
                   "csc" -> UnaryOperator.Csc
                   "log" -> UnaryOperator.Log
                   else -> throw Exception("Internal error : this line cannot be reached")
               }
            }
            else -> throw Exception("Internal error : this line cannot be reached")
        }
    }

    data class Num(val number: Double) : Token()

    data class Var(val name: String) : Token()

    object LParen : Token()

    object RParen : Token()

    object Div : Token()

    object Mul : Token()

    object Add : Token()

    object Sub : Token()

    object EOL : Token()
}
