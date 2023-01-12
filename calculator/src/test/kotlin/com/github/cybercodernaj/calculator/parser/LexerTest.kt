package com.github.cybercodernaj.calculator.parser

import kotlin.test.Test
import kotlin.test.assertEquals

class LexerTest {

    @Test fun makeTokens1() {
        val lexer = Lexer("123 + 345")
        val tokens = lexer.makeTokens()

        assertEquals(listOf(
            Token.Num(123.0),
            Token.Add,
            Token.Num(345.0),
            Token.EOL
        ), tokens)
    }

    @Test fun makeTokens2() {
        val lexer = Lexer("(4*2 - 4)/(4+ 2)")
        val tokens = lexer.makeTokens()

        assertEquals(listOf(
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
        ), tokens)
    }
}