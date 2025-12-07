package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day07 : AdventOfCode<Int, Long>("input") {
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

        val s = start()

        return loop(s.down(), setOf(s))
    }

    fun Diagram.countTimelines1(): Long {
        val c = mutableMapOf<Pos, Long>()

        fun loop(p: Pos): Long =
            c.getOrPut(p) {
                when {
                    p.y == size -> 1
                    this[p.y][p.x] == '^' -> p.split().sumOf { loop(it) }
                    else -> p.down().sumOf { loop(it) }
                }
            }

        return loop(start())
    }

    // After seeing the animation at:
    // https://www.reddit.com/r/adventofcode/comments/1pgbg8a/2025_day_7_part_2_visualization_for_the_sample/
    fun Diagram.countTimelines2(): Long {
        fun loop(y: Int, acc: Map<Int, Long>): Long =
            if (y == size) acc.values.sum()
            else {
                val splitters = get(y).mapIndexedNotNull { x, c -> if (c != '^') null else x }

                val updates =
                    acc.filterKeys { splitters.contains(it) }
                        .flatMap { (x, i) -> listOf(Pair(x - 1, i), Pair(x, -i), Pair(x + 1, i)) }

                val newAcc =
                    (acc.toList() + updates)
                        .groupBy { (x, _) -> x }
                        .mapValues { it.value.sumOf { (_, i) -> i } }

                loop(y + 1, acc.plus(newAcc))
            }

        return loop(start().y + 1, mapOf(Pair(start().x, 1))).takeIf { it == countTimelines1() }
            ?: error("countTimelines2() did not match countTimelines1()")
    }

    override fun part1(): Int = diagram.countBeamSplits()

    override fun part2(): Long = diagram.countTimelines2()
}

fun main() {
    Day07.solve()
}
