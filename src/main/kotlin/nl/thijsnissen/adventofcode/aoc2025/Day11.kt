package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day11 : AdventOfCode<Long, Long>("input") {
    typealias Devices = Map<String, List<String>>

    val devices: Devices = input().lines().associate { fromString(it) }.withDefault { emptyList() }

    fun fromString(s: String): Pair<String, List<String>> =
        s.split(":").let { it[0] to it[1].trim().split(" ") }

    fun Devices.paths(from: String, to: String): Long {
        val c = mutableMapOf<Pair<String, String>, Long>()

        fun loop(from: String, to: String): Long =
            c.getOrPut(from to to) { if (from == to) 1 else getValue(from).sumOf { loop(it, to) } }

        return loop(from, to)
    }

    override fun part1(): Long = devices.paths("you", "out")

    override fun part2(): Long =
        listOf(
                listOf("svr" to "fft", "fft" to "dac", "dac" to "out"),
                listOf("svr" to "dac", "dac" to "fft", "fft" to "out"),
            )
            .firstNotNullOf { it.map { devices.paths(it.first, it.second) }.takeUnless { 0 in it } }
            .reduce { acc, i -> acc * i }
}

fun main() {
    Day11.solve()
}
