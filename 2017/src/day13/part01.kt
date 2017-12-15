package day13

import java.io.File

data class Layer(val range: Int, val scanPosition: Int, val direction: Int) {
    fun roam(): Layer {
        val newPosition = scanPosition + direction
        return when {
            newPosition < 0 -> this.copy(scanPosition = 1, direction = +1)
            newPosition == range -> this.copy(scanPosition = range - 2, direction = -1)
            else -> this.copy(scanPosition = newPosition)
        }
    }
}

fun main(args: Array<String>) {
    val layers = File("day13/input.txt").readLines()
            .map { it.split(": ").map { it.toInt() } }
            .associateBy({ it[0] }, { Layer(it[1], 0, +1) })
            .toMutableMap() // k=layer number; v=f/w layer

    val damages = (0..layers.keys.max()!!).sumBy { packet ->
        val layer = layers[packet]
        val damage = if (layer?.scanPosition == 0) packet * layer.range else 0
        layers.forEach { n, l -> layers[n] = l.roam() }
        damage
    }

    println(damages)
}
