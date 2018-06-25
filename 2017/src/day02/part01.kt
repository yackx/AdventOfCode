package day02

import java.io.File

// Day 2: Corruption Checksum

fun main(args: Array<String>) {
    val checksum = File("src/day02/input.txt").readLines().sumBy { line ->
        val values: List<Int>  = line.split("\t").map { it.toInt() }
        values.max()!! - values.min()!!
    }
    println(checksum)
}