package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day03 : AdventOfCode<Long, Long>("input") {
    val banks: List<List<Int>> = input().lines().map { it.map { it.digitToInt() } }

    tailrec fun List<Int>.maxJoltage(n: Int, acc: String = ""): Long =
        if (n == 0) acc.toLong()
        else {
            val (i, m) = dropLast(n - 1).withIndex().maxBy { it.value }

            drop(i + 1).maxJoltage(n - 1, acc + m)
        }

    override fun part1(): Long = banks.sumOf { it.maxJoltage(2) }

    override fun part2(): Long = banks.sumOf { it.maxJoltage(12) }
}

fun main() {
    Day03.solve()
}
