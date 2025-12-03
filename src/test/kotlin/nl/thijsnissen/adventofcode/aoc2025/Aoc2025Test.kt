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
            Assertions.assertEquals(1227775554, Day02.part1())
            Assertions.assertEquals(4174379265, Day02.part2())
        }

        if (Day02.getExt() == "input") {
            Assertions.assertEquals(54234399924, Day02.part1())
            Assertions.assertEquals(70187097315, Day02.part2())
        }
    }

    @Test
    fun testDay03() {
        if (Day03.getExt() == "test") {
            Assertions.assertEquals(357, Day03.part1())
            Assertions.assertEquals(3121910778619, Day03.part2())
        }

        if (Day03.getExt() == "input") {
            Assertions.assertEquals(17435, Day03.part1())
            Assertions.assertEquals(172886048065379, Day03.part2())
        }
    }

    @Test
    fun testDay04() {
        if (Day04.getExt() == "test") {
            Assertions.assertEquals(0, Day04.part1())
            Assertions.assertEquals(0, Day04.part2())
        }

        if (Day04.getExt() == "input") {
            Assertions.assertEquals(0, Day04.part1())
            Assertions.assertEquals(0, Day04.part2())
        }
    }
}
