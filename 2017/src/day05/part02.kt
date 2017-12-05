package day05

import java.io.File

fun main(args: Array<String>) {
    val offsets = File("day05/input.txt").bufferedReader().readLines().map { it.toInt() }.toMutableList()
    var steps = 0
    var index = 0
    while (index >= 0 && index < offsets.size) {
        val prevIndex = index
        val prevOffset = offsets[index]
        index += offsets[index]
        offsets[prevIndex] += if (prevOffset >= 3) - 1 else 1
        steps++
    }
    println(steps)
}
