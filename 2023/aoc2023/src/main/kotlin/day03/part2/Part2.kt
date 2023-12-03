package day03.part2

fun parseForParts(lines: List<String>): List<EnginePart> {
    val numbersRegex = Regex("\\d+")
    return lines.flatMapIndexed { row, line ->
        numbersRegex.findAll(line).map { match ->
            val number = match.value.toInt()
            EnginePart(number, Position(row, match.range.first))
        }
    }
}

fun parseForGears(lines: List<String>): List<Position> =
    lines.flatMapIndexed { row, line ->
        line.mapIndexedNotNull { col, c ->
            if (c == '*') Position(row, col) else null
        }
    }

fun parse(lines: List<String>): Schematic =
    Schematic(parseForParts(lines), parseForGears(lines))

fun solve(schematic: Schematic): Int =
    schematic.gears.sumOf { gear ->
        val parts = schematic.engineParts.filter { it.neighbors().contains(gear) }
        if (parts.size == 2) parts[0].number * parts[1].number else 0
    }

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val schematic = parse(lines.toList())
    val result = solve(schematic)
    println(result)
}

data class Position(val row: Int, val col: Int)

data class EnginePart(val number: Int, val position: Position) {
    fun neighbors(): List<Position> {
        val (row, col) = position
        val length = number.toString().length
        val horizontalRange = IntRange(col - 1, col + length)
        return horizontalRange.map { Position(row - 1, it) } +
                horizontalRange.map { Position(row + 1, it) } +
                Position(row, col - 1) +
                Position(row, col + length)
    }
}

data class Schematic(val engineParts: List<EnginePart>, val gears: List<Position>)
