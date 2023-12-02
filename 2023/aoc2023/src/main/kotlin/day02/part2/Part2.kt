package day02.part2

import day02.Draw
import day02.parseGame


fun power(draws: List<Draw>): Int =
    listOf(Draw::red, Draw::green, Draw::blue)
        .map(draws::maxOf)
        .reduce(Int::times)


fun main() {
    val lines = generateSequence { readlnOrNull() }
    val totalScore = lines.map(::parseGame).sumOf { game ->
        power(game.second)
    }
    println(totalScore)
}
