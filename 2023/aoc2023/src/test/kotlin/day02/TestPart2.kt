package day02

import day02.part2.power
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TestPart2 {
    @Test
    fun testPower() {
        val expected = 4 * 22 * 23
        val actual = power(listOf(Draw(4, 0, 23), Draw(1, 22, 6), Draw(0, 2, 0)))
        assertEquals(expected, actual)
    }
}