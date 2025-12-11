package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day10 : AdventOfCode<Int, Int>("test") {
    val machines: List<Machine> = input().lines().map { Machine.fromString(it) }

    data class Machine(val target: Int, val buttons: List<Int>, val joltages: List<Int>) {
        fun configure(): Int {
            tailrec fun loop(todo: List<Pair<Int, Int>>, seen: Set<Int>): Int {
                val (s, c) = todo.first()

                return when (s) {
                    target -> c
                    in seen -> loop(todo.drop(1), seen)
                    else -> loop(todo.drop(1) + buttons.map { (s xor it) to (c + 1) }, seen + s)
                }
            }

            return loop(listOf(0 to 0), setOf())
        }

        companion object {
            fun empty(): Machine = Machine(0, emptyList(), emptyList())

            fun fromString(s: String): Machine =
                s.split(' ').fold(empty()) { acc, s ->
                    when {
                        s.startsWith("[") -> acc.copy(target = toTargetBitMask(s))
                        s.startsWith("(") -> acc.copy(buttons = acc.buttons + toButtonBitMask(s))
                        s.startsWith("{") -> acc.copy(joltages = toJoltages(s))
                        else -> acc
                    }
                }

            fun toButtonBitMask(s: String): Int =
                s.substring(1, s.length - 1).split(",").fold(0) { acc, i ->
                    acc or (1 shl i.toInt())
                }

            fun toTargetBitMask(s: String): Int =
                s.substring(1, s.length - 1).foldIndexed(0) { i, acc, c ->
                    if (c == '#') acc + (1 shl i) else acc
                }

            fun toJoltages(s: String): List<Int> =
                s.substring(1, s.length - 1).split(",").map { it.toInt() }
        }
    }

    override fun part1(): Int = machines.sumOf { it.configure() }

    override fun part2(): Int = 0
}

fun main() {
    Day10.solve()
}
