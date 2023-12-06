package day06.part2

fun solve(time: Long, distanceRecord: Long): Long =
    LongRange(0, time)
        .map { w -> w * (time - w) }
        .filter { it > distanceRecord }
        .fold(0L) { acc, _ -> acc + 1L }

fun parse(lines: List<String>): Pair<Long, Long> {
    val parseLine: (String) -> Long = { line ->
        val (_, values) = line.split(":")
        val numbersRegex = Regex("\\d+")
        numbersRegex.findAll(values).joinToString("") { match -> match.value }.toLong()
    }
    return parseLine(lines[0]) to parseLine(lines[1])
}

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val (time, distanceRecord) = parse(lines.toList())
    println(solve(time, distanceRecord))
}