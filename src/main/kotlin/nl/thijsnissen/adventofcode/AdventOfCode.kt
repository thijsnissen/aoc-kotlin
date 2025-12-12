package nl.thijsnissen.adventofcode

import kotlin.time.measureTimedValue

abstract class AdventOfCode<A, B>(private val ext: String) {
    abstract fun part1(): A

    open fun part2(): B? = null

    private val year: String =
        this::class
            .qualifiedName
            ?.split(".")
            ?.dropLast(1)
            ?.last()
            ?.filter { it.isDigit() }
            .orEmpty()

    private val day: String = this::class.simpleName?.filter { it.isDigit() }.orEmpty()

    fun getExt(): String = System.getenv("EXT") ?: ext

    fun input(): String =
        ClassLoader.getSystemResourceAsStream("$year/day$day.${getExt()}")?.use {
            it.bufferedReader().readText().trim()
        } ?: error("Resource not found!")

    fun solve() {
        val (a1, d1) = measureTimedValue { part1() }
        val (a2, d2) = measureTimedValue { part2() }

        println("The answer to $year day $day part 1 is: $a1 [${d1.inWholeMilliseconds}ms]")
        a2?.let { a ->
            println("The answer to $year day $day part 2 is: $a [${d2.inWholeMilliseconds}ms]")
        }
    }
}
