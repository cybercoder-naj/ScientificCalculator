package com.github.cybercodernaj.calculator.parser

import java.lang.StringBuilder

internal class Lexer(
    val function: String
) {
    private var position = 0
    private var currentChar = function[position]

    private fun advance() {
        currentChar = try {
            function[++position]
        } catch (ex: IndexOutOfBoundsException) {
            '\u0000'
        }
    }

    fun makeTokens(): List<Token> {
        val tokens = mutableListOf<Token>()

        while (currentChar != '\u0000') {
            when (currentChar) {
                '/' -> {
                    tokens.add(Token.Div)
                    advance()
                }

                '*' -> {
                    tokens.add(Token.Mul)
                    advance()
                }

                '+' -> {
                    tokens.add(Token.Add)
                    advance()
                }

                '-' -> {
                    tokens.add(Token.Sub)
                    advance()
                }

                '(' -> {
                    tokens.add(Token.LParen)
                    advance()
                }

                ')' -> {
                    tokens.add(Token.RParen)
                    advance()
                }

                else -> {
                    if (currentChar.isDigit()) {
                        val numSb = mutableListOf<Char>()
                        var dotCount = 0

                        while (currentChar != '\u0000' && (currentChar.isDigit() || currentChar == '.')) {
                            if (currentChar == '.') {
                                if (dotCount == 1)
                                    break
                                dotCount++
                            }
                            numSb.add(currentChar)
                            advance()
                        }

                        tokens.add(Token.Num(numSb.joinToString(separator = "").toDouble()))
                    } else if (currentChar.isLetter()) {
                        val name = StringBuilder()

                        while (currentChar != '\u0000' && currentChar.isLetter()) {
                            name.append(currentChar)
                            advance()
                        }

                        tokens.add(Token.Var(name.toString()))
                    } else if (currentChar.isWhitespace()) {
                        advance()
                        continue
                    } else
                        throw Exception()
                }
            }
        }

        return tokens.apply { add(Token.EOL) }
    }
}
