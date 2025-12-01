package nl.thijsnissen.adventofcode.aoc2025

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Aoc2025Test {
    @Test
    fun testDay01() {
        if (Day01.getExt() == "test") {
            Assertions.assertEquals(3, Day01.part1())
            Assertions.assertEquals(6, Day01.part2())
        }

        if (Day01.getExt() == "input") {
            Assertions.assertEquals(1195, Day01.part1())
            Assertions.assertEquals(6770, Day01.part2())
        }
    }

    @Test
    fun testDay02() {
        if (Day02.getExt() == "test") {
            Assertions.assertEquals(0, Day02.part1())
            Assertions.assertEquals(0, Day02.part2())
        }

        if (Day02.getExt() == "input") {
            Assertions.assertEquals(0, Day02.part1())
            Assertions.assertEquals(0, Day02.part2())
        }
    }
}
