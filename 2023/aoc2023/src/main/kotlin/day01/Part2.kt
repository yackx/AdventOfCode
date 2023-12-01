package day01

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val words = mapOf(
        "one" to "o1e",
        "two" to "t2o",
        "three" to "t3e",
        "four" to "4",
        "five" to "5e",
        "six" to "6",
        "seven" to "7n",
        "eight" to "e8t",
        "nine" to "n9e",
    )

    val digitize: (String) -> String = { line ->
        var digitized = line
        words.forEach { (letter, number) ->
            digitized = digitized.replace(letter, number)
        }
        digitized
    }

    val firstAndLast: (String) -> Int = { line ->
        val first = line.find { it.isDigit() }!!
        val last = line.findLast { it.isDigit() }!!
        "${first}${last}".toInt()
    }

    val calibrations = lines.map(digitize).sumOf(firstAndLast)
    println(calibrations)
}