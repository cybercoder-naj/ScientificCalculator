package com.github.cybercodernaj.calculator.parser

import kotlin.test.Test
import kotlin.test.assertEquals

class LexerTest {

    @Test
    fun makeTokens1() {
        val lexer = Lexer("123 + 345")
        val tokens = lexer.makeTokens()

        assertEquals(
            listOf(
                Token.Num(123.0),
                Token.Add,
                Token.Num(345.0),
                Token.EOL
            ), tokens
        )
    }

    @Test
    fun makeTokens2() {
        val lexer = Lexer("(4*2 - 4)/(4+ 2)")
        val tokens = lexer.makeTokens()

        assertEquals(
            listOf(
                Token.LParen,
                Token.Num(4.0),
                Token.Mul,
                Token.Num(2.0),
                Token.Sub,
                Token.Num(4.0),
                Token.RParen,
                Token.Div,
                Token.LParen,
                Token.Num(4.0),
                Token.Add,
                Token.Num(2.0),
                Token.RParen,
                Token.EOL
            ), tokens
        )
    }

    @Test
    fun makeTokens3() {
        val lexer = Lexer("(4.5 * 2 + 1) / 2.5")
        val tokens = lexer.makeTokens()

        assertEquals(
            listOf(
                Token.LParen,
                Token.Num(4.5),
                Token.Mul,
                Token.Num(2.0),
                Token.Add,
                Token.Num(1.0),
                Token.RParen,
                Token.Div,
                Token.Num(2.5),
                Token.EOL
            ), tokens
        )
    }

    @Test
    fun makeTokens4() {
        val lexer = Lexer("0.5 * m * v * v")
        val tokens = lexer.makeTokens()

        assertEquals(
            listOf(
                Token.Num(0.5),
                Token.Mul,
                Token.Var("m"),
                Token.Mul,
                Token.Var("v"),
                Token.Mul,
                Token.Var("v"),
                Token.EOL
            ),
            tokens
        )
    }

    @Test
    fun makeTokens5() {
        val lexer = Lexer("sin(pi/2)")
        val tokens = lexer.makeTokens()

        assertEquals(
            listOf(
                Token.Var("sin"),
                Token.LParen,
                Token.Var("pi"),
                Token.Div,
                Token.Num(2.0),
                Token.RParen,
                Token.EOL
            ),
            tokens
        )
    }
}