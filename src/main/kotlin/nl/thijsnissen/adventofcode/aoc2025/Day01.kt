package nl.thijsnissen.adventofcode.aoc2025

import kotlin.math.abs
import nl.thijsnissen.adventofcode.AdventOfCode

object Day01 : AdventOfCode<Int, Int>("input") {
    val instructions: List<Int> = input().lines().map(::parse)

    fun parse(s: String): Int {
        val direction = s.take(1)
        val clicks = s.drop(1).toInt()

        return if (direction == "L") clicks * -1 else clicks
    }

    data class Dial(val zeros: Int = 0, val pointer: Int = 50) {
        fun countZerosAfterRotation(instruction: Int): Dial {
            val nextPointer = nextPointer(instruction)

            return Dial(zeros + if (nextPointer == 0) 1 else 0, nextPointer)
        }

        fun countZerosDuringRotation(instruction: Int): Dial {
            val fullRounds = abs(instruction / SIZE)
            val remainder =
                when {
                    pointer + instruction % SIZE >= SIZE -> 1
                    pointer + instruction % SIZE <= 0 && pointer != 0 -> 1
                    else -> 0
                }

            return Dial(zeros + fullRounds + remainder, nextPointer(instruction))
        }

        private fun nextPointer(instruction: Int): Int = (pointer + instruction).mod(SIZE)

        companion object {
            const val SIZE = 100
        }
    }

    override fun part1(): Int = instructions.fold(Dial(), Dial::countZerosAfterRotation).zeros

    override fun part2(): Int = instructions.fold(Dial(), Dial::countZerosDuringRotation).zeros
}

fun main() {
    Day01.solve()
}
