package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day07 : AdventOfCode<Int, Int>("input") {
    typealias Diagram = List<String>

    val diagram: Diagram = input().lines()

    data class Pos(val x: Int, val y: Int) {
        fun down(): Set<Pos> = setOf(Pos(x, y + 1))

        fun split(): Set<Pos> = setOf(Pos(x - 1, y), Pos(x + 1, y))
    }

    fun Diagram.start(): Pos =
        first().indices.firstNotNullOf { c ->
            indices.firstNotNullOf { r -> if (this[c][r] != 'S') null else Pos(r, c) }
        }

    fun Diagram.exists(p: Pos): Boolean = p.y in indices && p.x in get(p.y).indices

    fun Diagram.countBeamSplits(): Int {
        tailrec fun loop(todo: Set<Pos>, seen: Set<Pos>, count: Int = 0): Int {
            fun Set<Pos>.validate(): List<Pos> = filter { exists(it) && !seen.contains(it) }

            val h = todo.firstOrNull()
            val t = h?.let { todo - it } ?: emptySet()

            return when {
                h == null -> count
                this[h.y][h.x] == '^' -> loop(t + h.split().validate(), seen + h, count + 1)
                else -> loop(t + h.down().validate(), seen + h, count)
            }
        }

        return loop(start().down(), setOf(start()))
    }

    fun Diagram.countTimelines(): Int = 0

    override fun part1(): Int = diagram.countBeamSplits()

    override fun part2(): Int = diagram.countTimelines()
}

fun main() {
    Day07.solve()
}
