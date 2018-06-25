package day02

import java.io.File

// Day 2: Corruption Checksum

fun allPairIndices(n: Int): Sequence<Pair<Int, Int>> {
    return generateSequence(Pair(0, 1), {
        var first = it.first
        var second = it.second + 1
        if (first == second) second++
        if (second >= n) {
            second = 0
            first++
        }
        if (first >= n) {
            null
        } else {
            Pair(first, second)
        }
    })
}

fun main(args: Array<String>) {
    val checksum = File("src/day02/input.txt").readLines().sumBy { line ->
        val values = line.split("\t").map { it.toInt() }
        allPairIndices(values.size)
                .filter { values[it.first] % values[it.second] == 0 }
                .map { values[it.first] / values[it.second] }
                .first()
    }
    println(checksum)
}