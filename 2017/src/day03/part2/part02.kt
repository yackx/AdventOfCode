package day03.part2

import day03.part2.Direction.*

private enum class Direction { UP, RIGHT, DOWN, LEFT }

data class Square(val pair: Pair<Int, Int>, val value: Int)

private fun squareSequence(): Sequence<Square> {
    val squares = mutableMapOf(Pair(0, 0) to 1).withDefault { 0 }
    var steps = 1       // how many steps for this leg
    var step = steps    // current step
    var dir = RIGHT

    // All -1..1 pair combinations expect (0,0) = neighbors deltas
    val deltas: MutableList<Pair<Int, Int>> = mutableListOf()
    (-1..1).forEach { x ->
        (-1..1).forEach { y ->
            if (x != 0 || y != 0)
                deltas.add(Pair(x, y))
        }
    }

    return generateSequence(Square(Pair(0, 0), 1), {
        step--
        // Find the next square position
        val pair = when (dir) {
            RIGHT -> {
                if (step == 0) {
                    step = steps
                    dir = UP
                }
                Pair(it.pair.first + 1, it.pair.second)
            }
            UP -> {
                if (step == 0) {
                    steps++
                    step = steps
                    dir = LEFT
                }
                Pair(it.pair.first, it.pair.second - 1)
            }
            LEFT -> {
                if (step == 0) {
                    step = steps
                    dir = DOWN
                }
                Pair(it.pair.first - 1, it.pair.second)
            }
            DOWN -> {
                if (step == 0) {
                    steps++
                    step = steps
                    dir = RIGHT
                }
                Pair(it.pair.first, it.pair.second + 1)
            }
        }

        // Add the values of all neighbors
        val sum = deltas.map { dp -> squares.getValue(Pair(pair.first + dp.first, pair.second + dp.second)) }.sum()
        // Store and return the new computed square (position+value)
        squares.put(pair, sum)
        Square(pair, sum)
    })
}

fun main(args: Array<String>) {
    val input = 289326
    val first = squareSequence().dropWhile { it.value < input }.first().value
    println(first)
}