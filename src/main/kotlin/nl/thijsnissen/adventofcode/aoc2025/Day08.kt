package nl.thijsnissen.adventofcode.aoc2025

import kotlin.math.pow
import kotlin.math.sqrt
import nl.thijsnissen.adventofcode.AdventOfCode

object Day08 : AdventOfCode<Long, Long>("input") {
    val boxes: List<Pos> = input().lines().map { Pos.fromString(it) }

    data class Pos(val x: Long, val y: Long, val z: Long) {
        fun euclidean(that: Pos): Double =
            sqrt(listOf(x - that.x, y - that.y, z - that.z).sumOf { it.toDouble().pow(2) })

        companion object {
            fun fromString(s: String): Pos =
                s.split(",").let { Pos(it[0].toLong(), it[1].toLong(), it[2].toLong()) }
        }
    }

    fun Set<Set<Pos>>.find(p: Pos): Set<Pos>? = find { it.contains(p) }

    fun Set<Set<Pos>>.union(a: Set<Pos>, b: Set<Pos>) =
        minusElement(a).minusElement(b).plusElement(a + b)

    data class State(val pairs: List<Pair<Pos, Pos>>, val acc: Set<Set<Pos>> = emptySet()) {
        fun makeConnection(): State {
            val (a, b) = pairs.first()
            val sa = acc.find(a) ?: setOf(a)
            val sb = acc.find(b) ?: setOf(b)

            return State(pairs.drop(1), acc.union(sa, sb))
        }

        fun makeConnections(n: Int): State =
            generateSequence(this) { it.makeConnection() }.drop(n).first()

        fun multiply(maxN: Int): Long =
            acc.map { it.size.toLong() }.sorted().takeLast(maxN).reduce { acc, s -> acc * s }

        fun connectAll(n: Int): Pair<Pos, Pos> =
            makeConnection().let {
                if (it.acc.firstOrNull()?.size == n) pairs.first() else it.connectAll(n)
            }

        companion object {
            fun fromBoxes(bs: List<Pos>): State =
                State(
                    bs.flatMapIndexed { i, a -> boxes.drop(i + 1).map { b -> a to b } }
                        .sortedBy { it.first.euclidean(it.second) }
                )
        }
    }

    override fun part1(): Long =
        State.fromBoxes(boxes).makeConnections(if (getExt() == "test") 10 else 1000).multiply(3)

    override fun part2(): Long =
        State.fromBoxes(boxes).connectAll(boxes.size).let { it.first.x * it.second.x }
}

fun main() {
    Day08.solve()
}
