package day06.part1

typealias Time = Int
typealias DistanceRecord = Int
typealias Race = Pair<Time, DistanceRecord>

fun solve(races: List<Race>): Int {
    fun solveRace(time: Time, distanceRecord: DistanceRecord): Int =
        IntRange(0, time).map { w -> w * (time - w) }.count { it > distanceRecord }
    return races.map { solveRace(it.first, it.second) }.reduce(Int::times)
}

fun parse(lines: List<String>): List<Race> {
    val parseLine: (String) -> List<Int> = { line ->
        val (_, values) = line.split(":")
        val numbersRegex = Regex("\\d+")
        numbersRegex.findAll(values).map { match -> match.value.toInt() }.toList()
    }
    val times = parseLine(lines[0])
    val distances = parseLine(lines[1])
    return times.zip(distances)
}

fun main() {
    val lines = generateSequence { readlnOrNull() }
    println(solve(parse(lines.toList())))
}