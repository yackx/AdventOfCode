package day01

import java.io.File

fun main(args: Array<String>) {
    val input = File("day01/input.txt").bufferedReader().use { it.readText() }.toCharArray()
    val size = input.size
    val sum = input.indices
            .filter { input[it] == input[(it+size/2)%size] }
            .map { input[it].toInt() - '0'.toInt() }
            .sum()
    println(sum)
}
