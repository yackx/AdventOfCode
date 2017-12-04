package day04.part1

import java.io.File

fun main(args: Array<String>) {
    val count = File("day04/input.txt").bufferedReader().lines()
            .filter {
                val splitted = it.split(" ")
                splitted.size == splitted.toSet().size
            }
           .count()
    println(count)
}