package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day01 : AdventOfCode<Int, Int>() {
    val day01 = input("input")

    override fun part1(): Int = day01.length

    override fun part2(): Int = day01.length
}

fun main() {
    Day01.solve()
}
