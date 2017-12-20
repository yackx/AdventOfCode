package day19.part2

import day19.part2.Direction.*
import java.io.File

typealias Diagram = List<String>

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Position(val x: Int, val y: Int)

class Network(val diagram: Diagram) {
    private var position: Position = Position(x = diagram[0].indexOf("|"), y = 0)
    private var direction = DOWN
    private var steps = 0

    private fun charAt(p: Position) = try { diagram[p.y][p.x].toString() } catch (e: IndexOutOfBoundsException) { "" }

    private fun hasCharAt(p: Position) = charAt(p) !in listOf("", " ")

    private fun adjacent(p: Position, d: Direction) = when (d) {
        DOWN -> p.copy(y = p.y + 1)
        RIGHT -> p.copy(x = p.x + 1)
        LEFT -> p.copy(x = p.x - 1)
        UP -> p.copy(y = p.y - 1)
    }

    private fun walk(): Boolean {
        if (charAt(position) == "+") {
            direction = when {
                hasCharAt(adjacent(position, UP)) && direction != DOWN -> Direction.UP
                hasCharAt(adjacent(position, DOWN)) && direction != UP -> Direction.DOWN
                hasCharAt(adjacent(position, RIGHT)) && direction != LEFT -> Direction.RIGHT
                hasCharAt(adjacent(position, LEFT)) && direction != RIGHT -> Direction.LEFT
                else -> throw IllegalStateException("+ at $position")
            }
        }
        position = adjacent(position, direction)
        steps++
        return hasCharAt(position)
    }

    fun solve(): Int {
        while (walk()) { }
        return steps
    }
}

fun main(args: Array<String>) {
    val diagram = File("day19/input.txt").bufferedReader().readLines()
    val count = Network(diagram).solve()
    println(count)
}