package nl.thijsnissen.adventofcode.aoc2025

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

object Aoc2025Test {
    @Test
    fun `Day 1 - Secret Entrance`() {
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
    fun `Day 2 - Gift Shop`() {
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
    fun `Day 3 - Lobby`() {
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
    fun `Day 4 - Printing Department`() {
        if (Day04.getExt() == "test") {
            Assertions.assertEquals(13, Day04.part1())
            Assertions.assertEquals(43, Day04.part2())
        }

        if (Day04.getExt() == "input") {
            Assertions.assertEquals(1569, Day04.part1())
            Assertions.assertEquals(9280, Day04.part2())
        }
    }

    @Test
    fun `Day 5 - Cafeteria`() {
        if (Day05.getExt() == "test") {
            Assertions.assertEquals(3, Day05.part1())
            Assertions.assertEquals(14, Day05.part2())
        }

        if (Day05.getExt() == "input") {
            Assertions.assertEquals(505, Day05.part1())
            Assertions.assertEquals(344423158480189, Day05.part2())
        }
    }

    @Test
    fun `Day 6 - Trash Compactor`() {
        if (Day06.getExt() == "test") {
            Assertions.assertEquals(4277556, Day06.part1())
            Assertions.assertEquals(3263827, Day06.part2())
        }

        if (Day06.getExt() == "input") {
            Assertions.assertEquals(6417439773370, Day06.part1())
            Assertions.assertEquals(11044319475191, Day06.part2())
        }
    }

    @Test
    fun `Day 7 - Laboratories`() {
        if (Day07.getExt() == "test") {
            Assertions.assertEquals(21, Day07.part1())
            Assertions.assertEquals(40, Day07.part2())
        }

        if (Day07.getExt() == "input") {
            Assertions.assertEquals(1602, Day07.part1())
            Assertions.assertEquals(135656430050438, Day07.part2())
        }
    }

    @Test
    fun `Day 8 - Playground`() {
        if (Day08.getExt() == "test") {
            Assertions.assertEquals(40, Day08.part1())
            Assertions.assertEquals(25272, Day08.part2())
        }

        if (Day08.getExt() == "input") {
            Assertions.assertEquals(75582, Day08.part1())
            Assertions.assertEquals(59039696, Day08.part2())
        }
    }

    @Test
    fun `Day 9 - Movie Theater`() {
        if (Day09.getExt() == "test") {
            Assertions.assertEquals(50, Day09.part1())
            Assertions.assertEquals(24, Day09.part2())
        }

        if (Day09.getExt() == "input") {
            Assertions.assertEquals(4715966250, Day09.part1())
            Assertions.assertEquals(1530527040, Day09.part2())
        }
    }

    @Test
    fun `Day 10 - Factory`() {
        if (Day10.getExt() == "test") {
            Assertions.assertEquals(0, Day10.part1())
            Assertions.assertEquals(0, Day10.part2())
        }

        if (Day10.getExt() == "input") {
            Assertions.assertEquals(0, Day10.part1())
            Assertions.assertEquals(0, Day10.part2())
        }
    }

    @Test
    fun `Day 11 - Reactor`() {
        if (Day11.getExt() == "test") {
            Assertions.assertEquals(8, Day11.part1())
            Assertions.assertEquals(2, Day11.part2())
        }

        if (Day11.getExt() == "input") {
            Assertions.assertEquals(658, Day11.part1())
            Assertions.assertEquals(371113003846800, Day11.part2())
        }
    }

    @Test
    fun `Day 12 - Christmas Tree Farm`() {
        if (Day12.getExt() == "test") {
            Assertions.assertEquals(2, Day12.part1())
        }

        if (Day12.getExt() == "input") {
            Assertions.assertEquals(463, Day12.part1())
        }
    }
}
