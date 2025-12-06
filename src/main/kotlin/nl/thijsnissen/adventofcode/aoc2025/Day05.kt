package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day05 : AdventOfCode<Int, Long>("input") {
    val ingredientIds = input().split("\n\n")
    val fresh: List<LongRange> = ingredientIds.first().lines().map(::toRange)
    val available: List<Long> = ingredientIds.last().lines().map { it.toLong() }

    fun toRange(s: String): LongRange = s.split("-").let { it.first().toLong()..it.last().toLong() }

    fun List<LongRange>.consolidate(): List<LongRange> {
        val rs = sortedWith(compareBy(LongRange::first, LongRange::last))

        return rs.drop(1).fold(rs.take(1)) { acc, c ->
            val p = acc.last()

            if (p.last < c.first) acc.plusElement(c)
            else acc.dropLast(1).plusElement(p.first..maxOf(p.last, c.last))
        }
    }

    override fun part1(): Int = available.count { id -> fresh.any { it.contains(id) } }

    override fun part2(): Long = fresh.consolidate().sumOf { it.last - it.first + 1 }
}

fun main() {
    Day05.solve()
}
