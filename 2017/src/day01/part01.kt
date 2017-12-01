package day01

import java.io.File

fun main(args: Array<String>) {
    val input = File("day01/input.txt").bufferedReader().use { it.readText() }
    val sum = input.indices
            .filter { input[it] == input[(it+1) % input.length] }
            .sumBy { Integer.valueOf(input[it].toString()) }
    println(sum)
}
