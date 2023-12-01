package day01

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val calibrations = lines.map { line ->
        val first = line.find { it.isDigit() }!!
        val last = line.findLast { it.isDigit() }!!
        "${first}${last}".toInt()
    }
    println(calibrations.sum())
}