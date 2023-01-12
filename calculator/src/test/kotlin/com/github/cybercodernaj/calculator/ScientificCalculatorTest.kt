package com.github.cybercodernaj.calculator

import kotlin.test.Test
import kotlin.test.assertEquals

class ScientificCalculatorTest {

    @Test fun solve1() {
        val ans = ScientificCalculator.solve("5+6")

        assertEquals(11.0, ans)
    }

    @Test fun solve2() {
        val ans = ScientificCalculator.solve("+30 - 40")

        assertEquals(-10.0, ans)
    }

    @Test fun solve3() {
        val ans = ScientificCalculator.solve("6*9+6+9")

        assertEquals(69.0, ans)
    }

    @Test fun solve4() {
        val ans = ScientificCalculator.solve("6*(9+6)+9")

        assertEquals(99.0, ans)
    }

    @Test fun solve5() {
        val ans = ScientificCalculator.solve("(4.5 * 2 + 1) / 2.5")

        assertEquals(4.0, ans)
    }

    @Test fun solve6() {
        val ans = ScientificCalculator.solve(
            function = "0.5 * m * v * v",
            environment = mapOf(
                "m" to 10.0,
                "v" to 15.0
            )
        )

        assertEquals(1125.0, ans)
    }

    @Test fun solve7() {
        val ans = ScientificCalculator.solve("sin(pi/2)")

        assertEquals(1.0, ans)
    }

    @Test fun solve8() {
        val ans = ScientificCalculator.solve("sin(pi/log(2))")

        assertEquals(-0.9838385294243626, ans)
    }
}
