package day02

import day02.part1.gameScore
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TestCommon {
    @Test
    fun testParseGame() {
        val line = "Game 10: 23 blue, 4 red; 1 red, 22 green, 6 blue; 2 green"
        val expected = 10 to listOf(Draw(4, 0, 23), Draw(1, 22, 6), Draw(0, 2, 0))
        val actual = parseGame(line)
        assertEquals(expected, actual)
    }

    @Test
    fun testGameScore() {
        val game = 5 to listOf(Draw(4, 0, 3), Draw(1, 2, 6), Draw(0, 2, 0))
        val expected = 5
        val actual = gameScore(game)
        assertEquals(expected, actual)
    }
}
