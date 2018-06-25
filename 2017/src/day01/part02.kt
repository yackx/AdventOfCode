package day01

import java.io.File

// Day 1: Inverse Captcha

fun main(args: Array<String>) {
    val input = File("src/day01/input.txt").bufferedReader().use { it.readText() }
    val size = input.length
    val sum = input.indices
            .filter { input[it] == input[(it+size/2) % size] }
            .sumBy { Character.getNumericValue(input[it]) }
    println(sum)
}
