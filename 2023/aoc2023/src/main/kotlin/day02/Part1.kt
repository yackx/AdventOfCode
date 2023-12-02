package day02

const val RED = 12
const val GREEN = 13
const val BLUE = 14

typealias Game = Pair<Int, List<Draw>>

fun parseGame(line: String): Game {
    fun parseDraw(drawStr: String): Draw {
        val parseColor: (String) -> Int = {
            if (it in drawStr) {
                val regex = "\\d+$".toRegex()
                val valueAtEndStr = drawStr.substringBefore(" $it")
                val match = regex.find(valueAtEndStr)
                match!!.value.toInt()
            }
            else 0
        }
        return Draw(parseColor("red"), parseColor("green"), parseColor("blue"))
    }

    val (idStr, drawsStr) = line.split(":")
    val id = idStr.substringAfter(" ").toInt()
    val draws = drawsStr.split(";").map { parseDraw(it) }
    return id to draws
}

fun gameScore(game: Game): Int =
    if (game.second.all { draw -> draw.isValid() }) game.first else 0


fun main() {
    val lines = generateSequence { readlnOrNull() }
    val totalScore = lines.map(::parseGame).sumOf(::gameScore)
    println(totalScore)
}


data class Draw(val red: Int, val green: Int, val blue: Int) {
    fun isValid(): Boolean = red <= RED && green <= GREEN && blue <= BLUE
}