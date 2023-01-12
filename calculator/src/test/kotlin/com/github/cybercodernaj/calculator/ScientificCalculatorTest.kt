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
}
