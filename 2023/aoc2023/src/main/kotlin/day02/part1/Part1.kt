package day02.part1

import day02.Game
import day02.parseGame

const val RED = 12
const val GREEN = 13
const val BLUE = 14

fun gameScore(game: Game): Int =
    if (game.second.all { draw -> draw.isValid() }) game.first else 0

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val totalScore = lines.map(::parseGame).sumOf(::gameScore)
    println(totalScore)
}
