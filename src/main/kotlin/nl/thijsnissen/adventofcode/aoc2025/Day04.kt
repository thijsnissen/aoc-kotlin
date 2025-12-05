package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode
import nl.thijsnissen.adventofcode.aoc2025.Day04.Roll.Companion.isAccessible
import nl.thijsnissen.adventofcode.aoc2025.Day04.Roll.Companion.removeAccessible

object Day04 : AdventOfCode<Int, Int>("input") {
    val rolls: Set<Roll> =
        input()
            .lines()
            .flatMapIndexed { y, l ->
                l.mapIndexedNotNull { x, c -> if (c != '@') null else Roll(x, y) }
            }
            .toSet()

    data class Roll(val x: Int, val y: Int) {
        operator fun plus(that: Roll): Roll = Roll(x + that.x, y + that.y)

        companion object {
            fun Set<Roll>.isAccessible(r: Roll): Boolean =
                setOf(
                        Roll(1, 0),
                        Roll(1, 1),
                        Roll(0, 1),
                        Roll(-1, 1),
                        Roll(-1, 0),
                        Roll(-1, -1),
                        Roll(0, -1),
                        Roll(1, -1),
                    )
                    .count { contains(r + it) } < 4

            tailrec fun Set<Roll>.removeAccessible(): Set<Roll> {
                val next = filterNot { isAccessible(it) }.toSet()

                return if (size == next.size) next else next.removeAccessible()
            }
        }
    }

    override fun part1(): Int = rolls.count { rolls.isAccessible(it) }

    override fun part2(): Int = rolls.size - rolls.removeAccessible().size
}

fun main() {
    Day04.solve()
}
