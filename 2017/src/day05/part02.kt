package day05

import java.io.File

fun main(args: Array<String>) {
    val offsets = File("src/day05/input.txt").bufferedReader().readLines().map { it.toInt() }.toMutableList()
    var steps = 0
    var index = 0
    while (index in offsets.indices) {
        val nextIndex = index + offsets[index]
        offsets[index] += if (offsets[index] >= 3) - 1 else 1
        index = nextIndex
        steps++
    }
    println(steps)
}
