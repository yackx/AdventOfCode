package day13.part2

import java.io.File

data class Layer(val range: Int, val scanPosition: Int, private val direction: Int) {
    fun roam(): Layer {
        val newPosition = scanPosition + direction
        return when {
            newPosition < 0 -> this.copy(scanPosition = 1, direction = +1)
            newPosition == range -> this.copy(scanPosition = range - 2, direction = -1)
            else -> this.copy(scanPosition = newPosition)
        }
    }

    companion object {
        fun roamAll(layers: Map<Int, Layer>): Map<Int, Layer> {
            val relayers = layers.toMutableMap()
            relayers.forEach { n, l -> relayers[n] = l.roam() }
            return relayers
        }
    }
}

fun walk(layers: Map<Int, Layer>, delay: Int): Boolean {
    var relayers = layers.toMap()
    println(delay)
    (1..delay).forEach { relayers = Layer.roamAll(relayers) }

    return (0..layers.keys.max()!!).any { packet ->
        val hit = relayers[packet]?.scanPosition == 0
        relayers = Layer.roamAll(relayers)
        hit
    }
}

fun main(args: Array<String>) {
    val layers = File("day13/input.txt").readLines()
            .map { it.split(": ").map { it.toInt() } }
            .associateBy({ it[0] }, { Layer(it[1], 0, +1) })
            .toMutableMap() // k=layer number; v=f/w layer

    val skip = generateSequence(0) { it + 1 }.dropWhile { walk(layers, it) }.first()
    println(skip)
}
