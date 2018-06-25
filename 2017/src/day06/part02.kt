package day06

import java.io.File

fun main(args: Array<String>) {
    val banks = File("src/day06/input.txt").bufferedReader().readLine()!!.split("\t").map { it.toInt() }.toMutableList()
    val seen = mutableListOf<List<Int>>()

    while (true) {
        val index = banks.indices.maxBy { idx -> banks[idx] }!!
        val value = banks[index]
        banks[index] = 0
        for (i in 1..value) {
            banks[(index+i) % banks.size]++
        }
        if (banks in seen) break
        seen.add(banks.toList())
    }

    println(seen.size - seen.indexOf(banks))
}