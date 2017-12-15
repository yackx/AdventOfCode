package day13.part2

import java.io.File

class Walker {
    fun scanPosition(layerSize: Int, cycles: Int): Int {
        val pos = cycles % (2 * layerSize - 2)
        return if (pos < layerSize) pos else 2 * layerSize - pos - 2
    }

    fun layersAt(layers: Map<Int, Int>, delay: Int) = layers.mapValues { it -> scanPosition(it.value, delay) }

    fun walk(layers: Map<Int, Int>, delay: Int): Boolean {
        var laterLayers = layersAt(layers, delay)

        return (0..layers.keys.max()!!).any { packet ->
            val hit = laterLayers[packet] == 0
            laterLayers = layersAt(layers, delay + packet + 1)
            hit
        }
    }

    fun waitTimeBeforeCrossing(layers: Map<Int, Int>) =
            generateSequence(0) { it + 1 }.dropWhile { walk(layers, it) }.first()
}

fun main(args: Array<String>) {
    val layers = File("day13/input.txt").readLines()
            .map { it.split(": ").map { it.toInt() } }
            .associateBy({ it[0] }, { it[1] })
            .toMutableMap() // k=layer number; v=layer range
    val wait = Walker().waitTimeBeforeCrossing(layers)
    println(wait)
}