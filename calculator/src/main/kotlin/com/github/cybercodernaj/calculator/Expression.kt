package com.github.cybercodernaj.calculator

import com.github.cybercodernaj.calculator.operations.BinaryOperator
import com.github.cybercodernaj.calculator.operations.UnaryOperator

internal sealed class Expression {
    data class Value(val value: Double) : Expression()
    data class Variable(val variable: String) : Expression()
    data class UnaryApplication(val operator: UnaryOperator, val expression: Expression) : Expression()
    data class BinaryApplication(val operator: BinaryOperator, val firstExpression: Expression, val secondExpression: Expression) : Expression()
}
