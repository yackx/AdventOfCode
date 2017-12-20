package day19.part1

import day19.part1.Direction.*
import java.io.File

typealias Diagram = List<String>

enum class Direction { UP, DOWN, LEFT, RIGHT }

data class Position(val x: Int, val y: Int)

class Network(val diagram: Diagram) {
    private var letters = mutableListOf<String>()
    private var position: Position = Position(x = diagram[0].indexOf("|"), y = 0)
    private var direction = DOWN

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
        charAt(position).apply { if (this in "A".."Z") letters.add(this) }
        return hasCharAt(position)
    }

    fun solve(): List<String> {
        while (walk()) { }
        return letters.toList()
    }
}

fun main(args: Array<String>) {
    val diagram = File("day19/input.txt").bufferedReader().readLines()
    val nodes = Network(diagram).solve()
    println(nodes.joinToString(""))
}