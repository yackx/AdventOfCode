package day02

import java.io.File

// Day 2: Corruption Checksum

fun main(args: Array<String>) {
    val checksum = File("src/day02/input.txt").readLines().sumBy { line ->
        val values = line.split("\t").map { it.toInt() }
        val v = fun(): Int {
            for (x in values) {
                for (y in values) {
                    if (x != y && x % y == 0) {
                        return x / y
                    }
                }
            }
            throw IllegalStateException(values.toString())
        }()
        v
    }
    println(checksum)
}