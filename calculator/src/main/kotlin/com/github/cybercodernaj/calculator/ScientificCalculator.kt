package com.github.cybercodernaj.calculator

import com.github.cybercodernaj.calculator.parser.Parser

class ScientificCalculator private constructor(
    internal val function: Expression
) {

    companion object {
        fun parse(function: String): ScientificCalculator {
            val expression = Parser.parse(function)
            return ScientificCalculator(expression)
        }
    }

    fun solve(environment: Map<String, Double> = emptyMap()) =
        solve(environment, function)

    private fun solve(environment: Map<String, Double>, function: Expression): Double {
        return when (function) {
            is Expression.Value -> function.value
            is Expression.Variable -> environment[function.variable]
                ?: throw NoSuchElementException("Variable ${function.variable} does not exist inside your environment")

            is Expression.UnaryApplication -> {
                val result = solve(environment, function.expression)

                function.operator(result)
            }

            is Expression.BinaryApplication -> {
                val firstResult = solve(environment, function.firstExpression)
                val secondResult = solve(environment, function.secondExpression)

                function.operator(firstResult, secondResult)
            }
        }
    }


}