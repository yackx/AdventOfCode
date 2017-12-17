package day16

import java.io.File
import java.util.*

/* Day 16 - Permutation Promenade */

fun dance(programs: List<String>, moves: List<String>): List<String> {
    val re = Regex("([sxp])(\\d+|\\w)/?(\\d+|\\w)?") // spin, exchange, partner x/x or n/n
    var p = programs.toMutableList()
    moves.forEach { danceMove ->
        val (move, param1, param2) = re.matchEntire(danceMove)!!.destructured
        when (move) {
            "s" -> {
                val spins = param1.toInt() % p.size
                val left = p.subList(p.size - spins, p.size).toMutableList()
                val right = p.subList(0, p.size - spins).toMutableList()
                p = left
                p.addAll(right)
            }
            "x" -> Collections.swap(p, param1.toInt(), param2.toInt())
            "p" -> Collections.swap(p, p.indexOf(param1), p.indexOf(param2))
            else -> throw IllegalArgumentException("Unknown dance move: $move")
        }
    }
    return p.toList()
}

fun main(args: Array<String>) {
    var programs = ('a'..'p').map { it.toString() }.toMutableList()
    val danceMoves = File("day16/input.txt").bufferedReader().readLine()!!.split(",")
    val seen = mutableListOf(programs.toList())

    var danced: List<String>
    while (true) {
        danced = dance(programs, danceMoves)    // dance the whole file
        if (danced in seen) break               // if seen this dance result before: bail out
        seen.add(danced)                        // store this dance
        programs = danced.toMutableList()       // and use it as the next dance starting point
    }

    val cycle = seen.size - seen.indexOf(danced)
    println(seen[1_000_000_000 % cycle].joinToString(""))
}
