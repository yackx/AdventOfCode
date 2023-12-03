package day03.part1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestPart1 {

    @Test
    fun testParseLineEnginePartsOnly() {
        val line = "467..114.."
        val expected = Schematic(
            listOf(
                EnginePart(467, Position(1, 0)),
                EnginePart(114, Position(1, 5)),
            ),
            emptyList()
        )
        val actual = parseLine(1, line)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseLineSymbolOnly() {
        val line = "...*......"
        val expected = Schematic(
            emptyList(),
            listOf(Position(5, 3))
        )
        val actual = parseLine(5, line)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseLineMixed() {
        val line = "617*......"
        val expected = Schematic(
            listOf(
                EnginePart(617, Position(3, 0)),
            ),
            listOf(Position(3, 3))
        )
        val actual = parseLine(3, line)
        assertEquals(expected, actual)
    }

    @Test
    fun testParseSymbolPartAlone() {
        val line = "114"
        val expected = Schematic(
            listOf(
                EnginePart(114, Position(1, 0)),
            ),
            emptyList()
        )
        val actual = parseLine(1, line)
        assertEquals(expected, actual)
    }

    @Test
    fun testSolveSample() {
        val input = """
            | 467..114..
            |...*......
            |..35..633.
            |......#...
            |617*......
            |.....+.58.
            |..592.....
            |......755.
            |...${'$'}.*....
            |.664.598..""".trimIndent()
        val schematicLines = input.lineSequence().mapIndexed { row, line -> parseLine(row, line) }
        val schematic = mergeSchematics(schematicLines.toList())
        val actual = solve(schematic)
        val expected = 4361
        assertEquals(expected, actual)
    }

    @Test
    fun testSolveSymbolAdjacentLeft() {
        val input = "#114"
        val schematicLines = input.lineSequence().mapIndexed { row, line -> parseLine(row, line) }
        val schematic = mergeSchematics(schematicLines.toList())
        val actual = solve(schematic)
        val expected = 114
        assertEquals(expected, actual)
    }
}
