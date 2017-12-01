package day01

import java.io.File

fun main(args: Array<String>) {
    val input = File("day01/input.txt").bufferedReader().use { it.readText() }
    val size = input.length
    val sum = input.indices
            .filter { input[it] == input[(it+size/2) % size] }
            .sumBy { Integer.valueOf(input[it].toString()) }
    println(sum)
}
