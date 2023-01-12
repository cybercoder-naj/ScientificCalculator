package com.github.cybercodernaj.calculator

import com.github.cybercodernaj.calculator.parser.Parser

object ScientificCalculator {
    fun solve(function: String, environment: Map<String, Double> = emptyMap()): Double {
        val parser = Parser(function)
        return solve(environment, parser.parse())
    }

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