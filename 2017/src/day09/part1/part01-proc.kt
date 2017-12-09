package day09.part1

import java.io.File

private fun score(s: String): Int {
    var score = 0
    var level = 0
    var ignoreNext = false
    var inGarbage = false
    for (c in s) {
        if (ignoreNext) {
            ignoreNext = false
            continue
        }
        when (c) {
            '{' -> if (!inGarbage) {
                    level++
                    score += level
                }
            '}' -> if (!inGarbage) level--
            '!' -> ignoreNext = true
            '<' -> inGarbage = true
            '>' -> inGarbage = false
        }
    }
    return score
}

fun main(args: Array<String>) {
    val input = File("day09/input.txt").readLines().first()
    println(score(input))
}

fun eaTests() {
    assert(score("{}") == 1)
    assert(score("{{{}}}") == 6)
    assert(score("{{},{}}") == 5)
    assert(score("{{{},{},{{}}}}") == 16)
    assert(score("{<a>,<a>,<a>,<a>}") == 1)
    assert(score("{{<ab>},{<ab>},{<ab>},{<ab>}}") == 9)
    assert(score("{{<!!>},{<!!>},{<!!>},{<!!>}}") == 9)
    assert(score("{{<a!>},{<a!>},{<a!>},{<ab>}}") == 3)
}