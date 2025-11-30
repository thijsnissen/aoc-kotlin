package nl.thijsnissen.adventofcode

import kotlin.time.measureTimedValue

abstract class AdventOfCode<A, B> {
    abstract fun part1(): A

    abstract fun part2(): B?

    private val year: String =
        this::class
            .qualifiedName
            ?.split(".")
            ?.dropLast(1)
            ?.last()
            ?.filter { c -> c.isDigit() }
            .orEmpty()

    private val day: String = this::class.simpleName?.filter { c -> c.isDigit() }.orEmpty()

    fun input(ext: String): String =
        ClassLoader.getSystemResourceAsStream("$year/day$day.${System.getenv("EXT") ?: ext}")
            ?.use { s -> s.bufferedReader().readText().trim() } ?: error("Resource not found!")

    fun solve() {
        val (a1, d1) = measureTimedValue { part1() }
        val (a2, d2) = measureTimedValue { part2() }

        println("The answer to $year day $day part 1 is: $a1 [${d1.inWholeMilliseconds}ms]")
        a2?.let { a ->
            println("The answer to $year day $day part 2 is: $a [${d2.inWholeMilliseconds}ms]")
        }
    }
}
