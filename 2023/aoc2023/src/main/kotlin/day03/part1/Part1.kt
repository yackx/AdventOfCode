package day03.part1


fun parseLine(row: Int, line: String): Schematic {
    val isPeriod: (Char) -> Boolean = { it == '.' }
    val isDigit: (Char) -> Boolean = { it.isDigit() }
    val isSymbol: (Char) -> Boolean = { !isPeriod(it) && !isDigit(it) }

    val engineParts = mutableListOf<EnginePart>()
    val symbols = mutableListOf<Position>()
    var state = ParserState.PERIOD
    var part = 0
    var col = 0

    line.forEachIndexed { index, c ->
        val previousState = state
        state = when {
            isPeriod(c) -> ParserState.PERIOD
            isDigit(c) -> ParserState.PART
            isSymbol(c) -> ParserState.SYMBOL
            else -> throw IllegalArgumentException("Unexpected character: $c")
        }
        when (state) {
            ParserState.PERIOD -> Unit
            ParserState.PART -> {
                if (previousState != ParserState.PART) {
                    col = index  // New part
                }
                part = part * 10 + c.toString().toInt()
            }
            ParserState.SYMBOL -> symbols.add(Position(row, index))
        }

        if (previousState == ParserState.PART && (state != ParserState.PART || index == line.length - 1)) {
            // Part is complete
            engineParts.add(EnginePart(part, Position(row, col)))
            part = 0
        }

    }
    return Schematic(engineParts, symbols)
}

fun mergeSchematics(schematics: List<Schematic>): Schematic =
    Schematic(schematics.flatMap { it.engineParts }, schematics.flatMap { it.symbols })

fun solve(schematic: Schematic): Int {
    fun neighbors(enginePart: EnginePart): List<Position> {
        val (row, col) = enginePart.position
        val length = enginePart.number.toString().length
        val horizontalRange = IntRange(col-1, col+length)
        return horizontalRange.map { Position(row-1, it) } +
                horizontalRange.map { Position(row+1, it) } +
                Position(row, col-1) +
                Position(row, col+length)
    }
    val validParts = schematic.engineParts.filter {
        neighbors(it).any { neighbor -> schematic.symbols.contains(neighbor) }
    }
    return validParts.sumOf { it.number }
}

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val schematicLines = lines.mapIndexed { index, line -> parseLine(index, line) }
    val schematic = mergeSchematics(schematicLines.toList())
    val result = solve(schematic)
    println(result)
}

data class Position(val row: Int, val col: Int)

data class EnginePart(val number: Int, val position: Position)

data class Schematic(val engineParts: List<EnginePart>, val symbols: List<Position>)

enum class ParserState { PART, SYMBOL, PERIOD }
