package com.github.cybercodernaj.calculator.parser

import com.github.cybercodernaj.calculator.Expression

internal class Parser(function: String) {
    private val tokens = Lexer(function).makeTokens()
    private var index = 0
    private var currentToken = tokens[index]

    class ParseResponse {
        var error: Throwable? = null
        var expr: Expression? = null

        fun register(obj: ParseResponse): Expression? {
            obj.error?.let {
                this.error = it
            }
            return obj.expr
        }

        fun success(expression: Expression) = apply {
            this.expr = expression
        }

        fun failure(error: Throwable) = apply {
            this.error = error
        }
    }

    private fun advance(): Token {
        if (++index in tokens.indices)
            currentToken = tokens[index]
        return currentToken
    }

    private fun factor(): ParseResponse {
        val response = ParseResponse()
        when (currentToken) {
            is Token.Sub, is Token.Add -> {
                val token = currentToken
                advance()
                val factor = response.register(factor())
                response.error?.let {
                    return response
                }
                return response.success(Expression.UnaryApplication(token.toUnaryOperator(), factor!!))
            }

            is Token.Num -> {
                val expr = Expression.Value((currentToken as Token.Num).number)
                advance()
                return response.success(expr)
            }

            is Token.LParen -> {
                advance()
                val contentExpr = response.register(expr())
                response.error?.let {
                    return response
                }
                return if (currentToken is Token.RParen) {
                    advance()
                    response.success(contentExpr!!)
                } else
                    response.failure(Exception())
            }

            else -> return response.failure(Exception("Expected Int or Double"))
        }
    }

    private fun term(): ParseResponse {
        return binaryOperation(::factor, Token.Mul, Token.Div)
    }

    private fun expr(): ParseResponse {
        return binaryOperation(::term, Token.Add, Token.Sub)
    }

    private fun binaryOperation(func: () -> ParseResponse, vararg operators: Token): ParseResponse {
        val response = ParseResponse()
        var left = response.register(func())
        response.error?.let {
            return response
        }

        while (currentToken in operators) {
            val operatorToken = currentToken
            advance()
            val right = response.register(func())
            response.error?.let {
                return response
            }
            left = Expression.BinaryApplication(operatorToken.toBinaryOperator(), left!!, right!!)
        }

        return response.success(left!!)
    }

    fun parse(): Expression {
        val result = expr()
        if (result.error != null)
            throw result.error!!
        else
            return result.expr!!
    }
}