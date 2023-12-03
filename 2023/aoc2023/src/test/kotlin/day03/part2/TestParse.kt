package day03.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestParse {
    @Test
    fun testParseLonePart() {
        val lines = listOf("467")
        val expected = listOf(EnginePart(467, Position(0, 0)))
        val actual = parseForParts(lines)
        assertEquals(expected, actual)
    }

    @Test
    fun testPartsSample() {
        val lines = """
            |467..114..
            |...*......
            |..35..633.
            |......#...
            |617*......
            |.....+.58.
            |..592.....
            |......755.
            |...${'$'}.*....
            |.664.598..
        """.trimMargin().split("\n")
        val expected = listOf(
            EnginePart(467, Position(0, 0)),
            EnginePart(114, Position(0, 5)),
            EnginePart(35, Position(2, 2)),
            EnginePart(633, Position(2, 6)),
            EnginePart(617, Position(4, 0)),
            EnginePart(58, Position(5, 7)),
            EnginePart(592, Position(6, 2)),
            EnginePart(755, Position(7, 6)),
            EnginePart(664, Position(9, 1)),
            EnginePart(598, Position(9, 5)),
        )
        val actual = parseForParts(lines)
        assertEquals(expected, actual)
    }

    @Test
    fun testGearsSample() {
        val lines = """
            |467..114..
            |...*......
            |..35..633.
            |......#...
            |617*......
            |.....+.58.
            |..592.....
            |......755.
            |...${'$'}.*....
            |.664.598..
        """.trimMargin().split("\n")
        val expected = listOf(
            Position(1, 3),
            Position(4, 3),
            Position(8, 5),
        )
        val actual = parseForGears(lines)
        assertEquals(expected, actual)
    }
}
