package nl.thijsnissen.adventofcode.aoc2025

import nl.thijsnissen.adventofcode.AdventOfCode

object Day06 : AdventOfCode<Long, Long>("input") {
    val worksheet: List<String> = input().lines()

    fun <T> List<List<T>>.transpose(): List<List<T>> =
        first().indices.map { c -> indices.map { r -> this[r][c] } }

    fun <T> List<T>.padEnd(i: Int, elem: T): List<T> = plus(List(i - size) { _ -> elem })

    data class Problem(val numbers: List<Long>, val operation: Char) {
        fun solve(): Long =
            when (operation) {
                '+' -> numbers.sum()
                '*' -> numbers.reduce { acc, n -> acc * n }
                else -> error("invalid operation")
            }

        companion object {
            fun fromMatchResults(mrs: List<MatchResult>): Problem =
                Problem(mrs.dropLast(1).map { it.value.toLong() }, mrs.last().value.first())

            fun fromPair(p: Pair<List<Long>, Char>): Problem = Problem(p.first, p.second)

            fun forNormalMath(ss: List<String>): List<Problem> =
                ss.map { """\S+""".toRegex().findAll(it).toList() }
                    .transpose()
                    .map(Problem::fromMatchResults)

            fun forCephalopodMath(ss: List<String>): List<Problem> {
                val numbers =
                    ss.dropLast(1)
                        .map { it.toList().padEnd(ss.maxOf(String::length), ' ') }
                        .transpose()
                        .fold(listOf(emptyList<Long>())) { acc, n ->
                            if (n.all(Char::isWhitespace)) acc.plusElement(emptyList())
                            else
                                acc.dropLast(1)
                                    .plusElement(
                                        acc.last().plusElement(n.joinToString("").trim().toLong())
                                    )
                        }
                val operations = ss.last().filterNot(Char::isWhitespace).toList()

                return numbers.zip(operations).map(Problem::fromPair)
            }
        }
    }

    override fun part1(): Long = Problem.forNormalMath(worksheet).sumOf(Problem::solve)

    override fun part2(): Long = Problem.forCephalopodMath(worksheet).sumOf(Problem::solve)
}

fun main() {
    Day06.solve()
}
