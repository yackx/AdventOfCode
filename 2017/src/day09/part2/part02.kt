package day09.part2

import java.io.File

private data class State(val garbages: Int, val ignoreNext: Boolean, val inGarbage: Boolean)

private fun countGarbage(s: String): Int =
    s.toCharArray()
            .fold(State(garbages = 0, ignoreNext = false, inGarbage = false), { state, c -> handle(state, c) })
            .garbages

private fun handle(state: State, c: Char): State {
    return if (state.ignoreNext) {
        state.copy(ignoreNext = false)
    } else {
        when (c) {
            '!' -> state.copy(ignoreNext = true)
            '<' -> if (state.inGarbage) state.copy(garbages = state.garbages + 1) else state.copy(inGarbage = true)
            '>' -> state.copy(inGarbage = false)
            else -> if (state.inGarbage) state.copy(garbages = state.garbages + 1) else state
        }
    }
}

fun main(args: Array<String>) {
    val input = File("day09/input.txt").readLines().first()
    println(countGarbage(input))
}
