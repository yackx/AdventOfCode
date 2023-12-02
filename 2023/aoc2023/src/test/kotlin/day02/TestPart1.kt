package day02

import day02.part1.gameScore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TestPart1 {

    @Test
    fun testGameScore() {
        val game = 5 to listOf(Draw(4, 0, 3), Draw(1, 2, 6), Draw(0, 2, 0))
        val expected = 5
        val actual = gameScore(game)
        assertEquals(expected, actual)
    }

    @Test
    fun testGameScoreZero() {
        val game = 1 to listOf(Draw(20, 0, 3), Draw(1, 2, 6), Draw(0, 2, 0))
        val expected = 0
        val actual = gameScore(game)
        assertEquals(expected, actual)
    }

    @Test
    fun testSample() {
        val input = """
            |Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            |Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            |Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            |Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            |Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green            
        """.trimIndent()
        val expected = 8
        val actual = input.lineSequence().map(::parseGame).sumOf(::gameScore)
        assertEquals(expected, actual)
    }
}