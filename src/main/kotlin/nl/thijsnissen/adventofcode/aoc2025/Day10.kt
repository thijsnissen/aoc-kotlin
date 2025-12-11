package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day10 : AdventOfCode<Int, Int>("input") {
    val machines: List<Machine> = input().lines().map { Machine.fromString(it) }

    data class Machine(val target: Int, val buttons: List<List<Int>>, val joltages: List<Int>) {
        fun configure(): Int {
            tailrec fun loop(todo: List<Pair<Int, Int>>, seen: Set<Int>): Int {
                val (s, c) = todo.first()

                return when (s) {
                    target -> c
                    in seen -> loop(todo.drop(1), seen)
                    else ->
                        loop(
                            todo.drop(1) + buttons.map { (s xor it.toBitMask()) to (c + 1) },
                            seen + s,
                        )
                }
            }

            return loop(listOf(0 to 0), setOf())
        }

        fun increase(): Int = 0 // TODO

        companion object {
            fun empty(): Machine = Machine(0, emptyList(), emptyList())

            fun fromString(s: String): Machine =
                s.split(' ').fold(empty()) { acc, s ->
                    when {
                        s.startsWith("[") -> acc.copy(target = s.toBitMask())
                        s.startsWith("(") ->
                            acc.copy(buttons = acc.buttons.plusElement(toButton(s)))
                        s.startsWith("{") -> acc.copy(joltages = toJoltages(s))
                        else -> acc
                    }
                }

            fun toButton(s: String): List<Int> =
                s.substring(1, s.length - 1).split(",").map { it.toInt() }

            fun List<Int>.toBitMask(): Int = fold(0) { acc, i -> acc or (1 shl i) }

            fun String.toBitMask(): Int =
                substring(1, length - 1).foldIndexed(0) { i, acc, c ->
                    if (c == '#') acc + (1 shl i) else acc
                }

            fun toJoltages(s: String): List<Int> =
                s.substring(1, s.length - 1).split(",").map { it.toInt() }
        }
    }

    override fun part1(): Int = machines.sumOf { it.configure() }

    override fun part2(): Int = machines.sumOf { it.increase() }
}

fun main() {
    Day10.solve()
}
