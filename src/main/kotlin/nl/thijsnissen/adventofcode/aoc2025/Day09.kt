package nl.thijsnissen.adventofcode.aoc2025

import kotlin.math.abs
import nl.thijsnissen.adventofcode.AdventOfCode

object Day09 : AdventOfCode<Long, Long>("input") {
    val tiles: List<Pos> = input().lines().map { Pos.fromString(it) }

    data class Pos(val x: Long, val y: Long) {
        fun area(that: Pos): Long = (abs(x - that.x) + 1) * (abs(y - that.y) + 1)

        companion object {
            fun fromString(s: String): Pos =
                s.split(",").let { Pos(it[0].toLong(), it[1].toLong()) }

            fun maxArea(ts: List<Pos>): Long =
                ts.mapIndexedNotNull { i, ta -> ts.drop(i + 1).maxOfOrNull { tb -> ta.area(tb) } }
                    .max()
        }
    }

    override fun part1(): Long = Pos.maxArea(tiles)

    override fun part2(): Long = 0
}

fun main() {
    Day09.solve()
}
