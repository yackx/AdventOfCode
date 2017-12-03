package day03.part1

import day03.part1.Direction.*
import kotlin.math.absoluteValue

enum class Direction { UP, RIGHT, DOWN, LEFT }

private fun gridSequence(): Sequence<Pair<Int, Int>> {
    var steps = 1       // how many steps for this leg
    var step = steps    // current step
    var dir = RIGHT

    return generateSequence(Pair(0, 0), {
        step--
        when (dir) {
            RIGHT -> {
                if (step == 0) {
                    step = steps
                    dir = UP
                }
                Pair(it.first + 1, it.second)
            }
            UP -> {
                if (step == 0) {
                    steps++
                    step = steps
                    dir = LEFT
                }
                Pair(it.first, it.second - 1)
            }
            LEFT -> {
                if (step == 0) {
                    step = steps
                    dir = DOWN
                }
                Pair(it.first - 1, it.second)
            }
            DOWN -> {
                if (step == 0) {
                    steps++
                    step = steps
                    dir = RIGHT
                }
                Pair(it.first, it.second + 1)
            }
        }
    })
}

fun square(n: Int): Pair<Int, Int> = gridSequence().take(n).last()

fun distance(p: Pair<Int, Int>) = p.first.absoluteValue + p.second.absoluteValue

fun main(args: Array<String>) {
    println(gridSequence().take(2).toList())
    assert(distance(square(1)) == 0)
    assert(distance(square(12)) == 3)
    assert(distance(square(23)) == 2)
    assert(distance(square(1024)) == 31)
    println(distance(square(289326)))
}