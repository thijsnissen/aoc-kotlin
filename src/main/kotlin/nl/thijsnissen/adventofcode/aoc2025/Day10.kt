package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day10 : AdventOfCode<Int, Int>("input") {
    val machines: List<Machine> = input().lines().map { Machine.fromString(it) }

    data class Machine(val target: Int, val buttons: List<List<Int>>, val joltages: List<Int>) {
        fun configureIndicatorLights(): Int =
            buttons
                .combinations()
                .filter { target == it.fold(0) { acc, b -> acc xor b.toBitMask() } }
                .minOf { it.size }

        fun configureCounters(): Int {
            val c = mutableMapOf<List<Int>, Int?>()

            fun loop(js: List<Int>): Int? =
                c.getOrPut(js) {
                    if (js.all { it == 0 }) 0
                    else {
                        patterns.entries.fold(null) { acc, (p, s) ->
                            // Pattern should not exceed target and should match odd/even parity
                            if (!p.indices.all { p[it] <= js[it] && p[it] % 2 == js[it] % 2 }) acc
                            else
                                loop(js.mapIndexed { i, j -> (j - p[i]) / 2 })?.let { n ->
                                    minOf(acc ?: Int.MAX_VALUE, s + 2 * n)
                                } ?: acc
                        }
                    }
                }

            return loop(joltages) ?: error("unable to configure joltage level counters: $joltages")
        }

        private val patterns: Map<List<Int>, Int> by lazy {
            buttons
                .combinations()
                .map { bs ->
                    val indices = bs.flatten()

                    joltages.indices.map { i -> indices.count { i == it } } to bs.size
                }
                .groupBy({ it.first }, { it.second })
                .mapValues { it.value.min() }
        }

        companion object {
            fun empty(): Machine = Machine(0, emptyList(), emptyList())

            fun fromString(s: String): Machine =
                s.split(' ').fold(empty()) { acc, s ->
                    when {
                        s.startsWith("[") -> acc.copy(target = s.toBitMask())
                        s.startsWith("(") ->
                            acc.copy(buttons = acc.buttons.plusElement(s.toButton()))
                        s.startsWith("{") -> acc.copy(joltages = s.toJoltages())
                        else -> acc
                    }
                }

            fun String.toButton(): List<Int> =
                removeSurrounding("(", ")").split(",").map { it.toInt() }

            fun String.toJoltages(): List<Int> =
                removeSurrounding("{", "}").split(",").map { it.toInt() }

            fun List<Int>.toBitMask(): Int = fold(0) { acc, i -> acc or (1 shl i) }

            fun String.toBitMask(): Int =
                removeSurrounding("[", "]").foldIndexed(0) { i, acc, c ->
                    if (c == '#') acc + (1 shl i) else acc
                }
        }
    }

    fun <T> List<T>.combinations(): List<List<T>> = (0..size).flatMap { combinations(it) }

    fun <T> List<T>.combinations(size: Int): List<List<T>> =
        when {
            size == 0 -> listOf(emptyList())
            isEmpty() -> emptyList()
            else -> {
                val h = listOf(first())
                val t = drop(1)

                t.combinations(size - 1).map { h + it } + t.combinations(size)
            }
        }

    override fun part1(): Int = machines.sumOf { it.configureIndicatorLights() }

    /**
     * Solved part 2 using the wonderful solution proposed by u/tenthmascot at:
     * https://www.reddit.com/r/adventofcode/comments/1pk87hl/2025_day_10_part_2_bifurcate_your_way_to_victory/
     */
    override fun part2(): Int = machines.sumOf { it.configureCounters() }
}

fun main() {
    Day10.solve()
}
