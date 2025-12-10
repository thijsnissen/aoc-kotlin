package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day09 : AdventOfCode<Long, Long>("input") {
    typealias Tile = Pair<Long, Long>

    val tiles: List<Tile> = input().lines().map { fromString(it) }

    fun fromString(s: String): Tile = s.split(",").let { it[0].toLong() to it[1].toLong() }

    data class Box(val xMin: Long, val yMin: Long, val xMax: Long, val yMax: Long) {
        fun area(): Long = (xMax - xMin + 1) * (yMax - yMin + 1)

        fun intersection(that: Box): Boolean =
            xMin < that.xMax && xMax > that.xMin && yMin < that.yMax && yMax > that.yMin

        companion object {
            fun fromTiles(t1: Tile, t2: Tile): Box =
                Box(
                    xMin = minOf(t1.first, t2.first),
                    yMin = minOf(t1.second, t2.second),
                    xMax = maxOf(t1.first, t2.first),
                    yMax = maxOf(t1.second, t2.second),
                )

            fun combinations(ts: List<Tile>): List<Box> =
                ts.flatMapIndexed { i, t1 -> ts.drop(i + 1).map { t2 -> fromTiles(t1, t2) } }

            fun perimeter(ts: List<Tile>): List<Box> =
                ts.zipWithNext().plusElement(ts.last() to ts.first()).map {
                    fromTiles(it.first, it.second)
                }
        }
    }

    override fun part1(): Long = Box.combinations(tiles).maxOf { it.area() }

    override fun part2(): Long {
        val boxes = Box.combinations(tiles).sortedBy { -it.area() }
        val lines = Box.perimeter(tiles).sortedBy { -it.area() }

        return boxes.first { b -> !lines.any { l -> b.intersection(l) } }.area()
    }
}

fun main() {
    Day09.solve()
}
