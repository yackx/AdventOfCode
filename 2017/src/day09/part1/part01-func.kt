package day09.part1

import java.io.File

private data class State(val score: Int, val level: Int, val ignoreNext: Boolean, val inGarbage: Boolean)

private fun score(s: String): Int =
    s.toCharArray()
            .fold(State(score = 0, level = 0, ignoreNext = false, inGarbage = false), { state, c -> handle(state, c) })
            .score

private fun handle(state: State, c: Char): State {
    return if (state.ignoreNext) {
        state.copy(ignoreNext = false)
    } else {
        when (c) {
            '{' ->
                if (!state.inGarbage)
                    state.copy(level = state.level + 1, score = state.score + state.level + 1)
                else
                    state
            '}' ->
                if (!state.inGarbage)
                    state.copy(level = state.level - 1)
                else
                    state
            '!' -> state.copy(ignoreNext = true)
            '<' -> state.copy(inGarbage = true)
            '>' -> state.copy(inGarbage = false)
            else -> state
        }
    }
}

fun main(args: Array<String>) {
    val input = File("src/day09/input.txt").readLines().first()
    println(score(input))
}
