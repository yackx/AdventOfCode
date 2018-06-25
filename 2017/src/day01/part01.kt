package day01

import java.io.File

// Day 1: Inverse Captcha

fun main(args: Array<String>) {
    val input = File("src/day01/input.txt").bufferedReader().use { it.readText() }
    val sum = input.indices
            .filter { input[it] == input[(it+1) % input.length] }
            .sumBy { Character.getNumericValue(input[it]) }
    println(sum)
}
