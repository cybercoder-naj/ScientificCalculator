package com.github.cybercodernaj.calculator.parser

import com.github.cybercodernaj.calculator.Expression.*
import com.github.cybercodernaj.calculator.operations.BinaryOperator
import com.github.cybercodernaj.calculator.operations.UnaryOperator
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class ParserTest {

    @Test
    fun parse1() {
        val parser = Parser("123 + 345")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Add,
                Value(123.0),
                Value(345.0)
            ), expression
        )
    }

    @Test
    fun parse2() {
        val parser = Parser("6 + 5 * 2")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Add,
                Value(6.0),
                BinaryApplication(
                    BinaryOperator.Mul,
                    Value(5.0),
                    Value(2.0)
                )
            ), expression
        )
    }

    @Test
    fun parse3() {
        val parser = Parser("1+2+3+4")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Add,
                BinaryApplication(
                    BinaryOperator.Add,
                    BinaryApplication(
                        BinaryOperator.Add,
                        Value(1.0),
                        Value(2.0)
                    ),
                    Value(3.0)
                ),
                Value(4.0)
            ), expression
        )
    }

    @Test
    fun parse4() {
        val parser = Parser("1+2+3+")

        assertThrows<Exception> {
            parser.parse()
        }
    }

    @Test
    fun parse5() {
        val parser = Parser("(6 + 5) * 2")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Mul,
                BinaryApplication(
                    BinaryOperator.Add,
                    Value(6.0),
                    Value(5.0)
                ),
                Value(2.0)
            ), expression
        )
    }

    @Test
    fun parse6() {
        val parser = Parser("-5")
        val expression = parser.parse()

        assertEquals(
            UnaryApplication(
                UnaryOperator.Neg,
                Value(5.0)
            ), expression
        )
    }

    @Test
    fun parse7() {
        val parser = Parser("-5 + 6 * 7")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Add,
                UnaryApplication(
                    UnaryOperator.Neg,
                    Value(5.0)
                ),
                BinaryApplication(
                    BinaryOperator.Mul,
                    Value(6.0),
                    Value(7.0)
                )
            ), expression
        )
    }

    @Test
    fun parse8() {
        val parser = Parser("(-5 + 6) * 7")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Mul,
                BinaryApplication(
                    BinaryOperator.Add,
                    UnaryApplication(
                        UnaryOperator.Neg,
                        Value(5.0)
                    ),
                    Value(6.0)
                ),
                Value(7.0)
            ), expression
        )
    }

    @Test
    fun parse9() {
        val parser = Parser("(4.5 * 2 + 1) / 2.5")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Div,
                BinaryApplication(
                    BinaryOperator.Add,
                    BinaryApplication(
                        BinaryOperator.Mul,
                        Value(4.5),
                        Value(2.0)
                    ),
                    Value(1.0)
                ),
                Value(2.5)
            ), expression
        )
    }

    @Test
    fun parse10() {
        val parser = Parser("0.5 * m * v * v")
        val expression = parser.parse()

        assertEquals(
            BinaryApplication(
                BinaryOperator.Mul,
                BinaryApplication(
                    BinaryOperator.Mul,
                    BinaryApplication(
                        BinaryOperator.Mul,
                        Value(0.5),
                        Variable("m")
                    ),
                    Variable("v")
                ),
                Variable("v")
            ), expression
        )
    }

    @Test
    fun parse11() {
        val parser = Parser("sin(pi/log(2))")
        val expression = parser.parse()

        assertEquals(
            UnaryApplication(
                UnaryOperator.Sin,
                BinaryApplication(
                    BinaryOperator.Div,
                    Variable("pi"),
                    UnaryApplication(
                        UnaryOperator.Log,
                        Value(2.0)
                    )
                )
            ), expression
        )
    }
}