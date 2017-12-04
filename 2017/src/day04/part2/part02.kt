package day04.part2

import java.io.File

private fun areAnagrams(s1: String, s2: String) = s1.toSortedSet() == s2.toSortedSet()

fun main(args: Array<String>) {
    val count = File("day04/input.txt").bufferedReader().lines()
            .filter { line ->
                val split = line.split(" ")
                var anagramFound = false
                for ((index, word) in split.withIndex()) {
                    anagramFound = split.toList()
                            .filterIndexed { idx, _ -> idx != index }
                            .any { areAnagrams(it, word) }
                    if (anagramFound) break
                }
                !anagramFound
            }
           .count()
    println(count)
}
