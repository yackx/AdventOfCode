package day02

import day02.part1.BLUE
import day02.part1.GREEN
import day02.part1.RED

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

data class Draw(val red: Int, val green: Int, val blue: Int) {
    fun isValid(): Boolean = red <= RED && green <= GREEN && blue <= BLUE
}