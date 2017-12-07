package day04.part2

import java.io.File

fun main(args: Array<String>) {
    val count = File("day04/input.txt").bufferedReader().lines()
            .filter { line ->
                val words = line.split(" ")
                words.map { it.toSortedSet() }.distinct().size == words.size
            }
            .count()
    println(count)
}
