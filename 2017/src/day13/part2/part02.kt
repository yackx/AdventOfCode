package day13.part2

import java.io.File

/*
This solution works with the sample, but fails on the actual input because of the caching.
java.lang.OutOfMemoryError: GC overhead limit exceeded
Without caching, it is even slower to progress and it won't complete in a timely manner.
 */

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

class PacketWalker(val layers: Map<Int, Layer>) {
    var waitedStates = mutableMapOf<Int, Map<Int, Layer>>()

    private fun walk(delay: Int): Boolean {
        println(delay)

        var relayers = if (delay > 0) Layer.roamAll(waitedStates[delay - 1]!!) else layers
        waitedStates[delay] = HashMap(relayers)//relayers.toMap()

        return (0..layers.keys.max()!!).any { packet ->
            val hit = relayers[packet]?.scanPosition == 0
            relayers = Layer.roamAll(relayers)
            hit
        }
    }

    fun waitTimeBeforeCrossing() = generateSequence(0) { it + 1 }.dropWhile { walk(it) }.first()
}

fun main(args: Array<String>) {
    val layers = File("day13/input.txt").readLines()
            .map { it.split(": ").map { it.toInt() } }
            .associateBy({ it[0] }, { Layer(it[1], 0, +1) })
            .toMutableMap() // k=layer number; v=f/w layer

    val packetWalker = PacketWalker(layers)
    println(packetWalker.waitTimeBeforeCrossing())
}
