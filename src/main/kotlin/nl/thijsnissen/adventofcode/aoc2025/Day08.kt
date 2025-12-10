package nl.thijsnissen.adventofcode.aoc2025

import kotlin.math.pow
import kotlin.math.sqrt
import nl.thijsnissen.adventofcode.AdventOfCode
import nl.thijsnissen.adventofcode.aoc2025.Day08.State.Companion.connectAll

object Day08 : AdventOfCode<Int, Int>("input") {
    val boxes: List<Box> = input().lines().map { Box.fromString(it) }

    data class Box(val x: Int, val y: Int, val z: Int) {
        fun euclidean(that: Box): Double =
            sqrt(listOf(x - that.x, y - that.y, z - that.z).sumOf { it.toDouble().pow(2) })

        companion object {
            fun fromString(s: String): Box =
                s.split(",").let { Box(it[0].toInt(), it[1].toInt(), it[2].toInt()) }
        }
    }

    fun Set<Set<Box>>.find(b: Box): Set<Box>? = find { it.contains(b) }

    fun Set<Set<Box>>.union(a: Set<Box>, b: Set<Box>) =
        minusElement(a).minusElement(b).plusElement(a + b)

    data class State(val ps: List<Pair<Box, Box>>, val acc: Set<Set<Box>> = emptySet()) {
        fun makeConnection(): State {
            val (a, b) = ps.first()
            val sa = acc.find(a) ?: setOf(a)
            val sb = acc.find(b) ?: setOf(b)

            return State(ps.drop(1), acc.union(sa, sb))
        }

        fun makeConnections(n: Int): State =
            generateSequence(this) { it.makeConnection() }.drop(n).first()

        fun multiply(maxN: Int): Int =
            acc.map { it.size }.sorted().takeLast(maxN).reduce { acc, s -> acc * s }

        companion object {
            fun fromBoxes(bs: List<Box>): State =
                State(
                    bs.flatMapIndexed { i, a -> boxes.drop(i + 1).map { b -> a to b } }
                        .sortedBy { it.first.euclidean(it.second) }
                )

            // This function is on the companion object to be able to make it tail recursive.
            tailrec fun State.connectAll(n: Int): Pair<Box, Box> {
                val ns = makeConnection()

                return if (ns.acc.firstOrNull()?.size == n) ps.first() else ns.connectAll(n)
            }
        }
    }

    override fun part1(): Int =
        State.fromBoxes(boxes).makeConnections(if (getExt() == "test") 10 else 1000).multiply(3)

    override fun part2(): Int =
        State.fromBoxes(boxes).connectAll(boxes.size).let { it.first.x * it.second.x }
}

fun main() {
    Day08.solve()
}
