package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day02 : AdventOfCode<Long, Long>("input") {
    val idRanges: List<LongRange> = input().split(",").map(::toRange)

    fun toRange(s: String): LongRange {
        val start = s.takeWhile { it != '-' }
        val end = s.drop(start.length + 1)

        return start.toLong()..end.toLong()
    }

    fun Long.isInvalidId(): Boolean =
        toString().let { it.take(it.length / 2) == it.drop(it.length / 2) }

    fun Long.isInvalidId(groupSizes: IntProgression): Boolean =
        groupSizes.any { toString().chunked(it).distinct().size == 1 }

    fun List<LongRange>.sumOfIds(predicate: (Long) -> Boolean): Long = sumOf {
        it.filter { predicate(it) }.sum()
    }

    override fun part1(): Long = idRanges.sumOfIds { it.isInvalidId() }

    override fun part2(): Long =
        idRanges.sumOfIds { it.isInvalidId(it.toString().length / 2 downTo 1) }
}

fun main() {
    Day02.solve()
}
