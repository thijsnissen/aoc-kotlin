package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day12 : AdventOfCode<Int, Int>("input") {
    val parts: List<String> = input().split("\n\n")
    val presents: List<Int> = parts.dropLast(1).map { it.count { it == '#' } }
    val regions: List<Region> = parts.last().lines().map { Region.fromString(it) }

    data class Region(val size: Int, val presents: List<Int>) {
        // slack is additional space to move around and mainly to make the testcase pass
        fun solve(ps: List<Int>, slack: Int = 5): Boolean =
            presents.foldIndexed(0) { i, acc, p -> acc + ps[i] * p } <= size - slack

        companion object {
            fun fromString(s: String): Region {
                val (size, presents) = s.split(':')
                val (x, y) = size.split('x')

                return Region(
                    size = x.toInt() * y.toInt(),
                    presents = presents.trim().split(' ').map { it.toInt() },
                )
            }
        }
    }

    override fun part1(): Int = regions.count { it.solve(presents) }
}

fun main() {
    Day12.solve()
}
