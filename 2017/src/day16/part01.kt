package day16

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val re = Regex("([sxp])(\\d+|\\w)/?(\\d+|\\w)?")
    var programs = ('a'..'p').map { it.toString() }.toMutableList()
    File("day16/input.txt").bufferedReader().readLine()!!.split(",").forEach { action ->
        val (move, param1, param2) = re.matchEntire(action)!!.destructured
        when (move) {
            "s" -> {
                val spins = param1.toInt() % programs.size
                val left = programs.subList(programs.size - spins, programs.size)
                val right = programs.subList(0, programs.size - spins)
                programs = left
                programs.addAll(right)
            }
            "x" -> Collections.swap(programs,  param1.toInt(), param2.toInt())
            "p" -> Collections.swap(programs, programs.indexOf(param1), programs.indexOf(param2))
            else -> throw IllegalArgumentException("Unknown dance move: $move")
        }
    }
    println(programs.joinToString(""))
}