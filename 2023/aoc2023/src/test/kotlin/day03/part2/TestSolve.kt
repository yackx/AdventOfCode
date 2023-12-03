package day03.part2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestSolver {
    @Test
    fun testSolveSample() {
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
        val schematic = parse(lines)
        val expected = 467835
        val actual = solve(schematic)
        assertEquals(expected, actual)
    }
}