package day13.part2

import java.io.File

class Walker {
    fun scanPosition(layerSize: Int, cycles: Int): Int {
        val pos = cycles % (2 * layerSize - 2)
        return if (pos < layerSize) pos else 2 * layerSize - pos - 2
    }

    fun walk(firewall: Map<Int, Int>, delay: Int): Boolean {
        return (0..firewall.keys.max()!!).any { packet ->       // hit on any of these steps
            firewall.containsKey(packet) && scanPosition(firewall[packet]!!, packet + delay) == 0
        }
    }

    fun waitTimeBeforeCrossing(layers: Map<Int, Int>) =
            generateSequence(0) { it + 1 }.dropWhile { walk(layers, it) }.first()
}

fun main(args: Array<String>) {
    val layers = File("src/day13/input.txt").readLines()
            .map { it.split(": ").map { it.toInt() } }
            .associateBy({ it[0] }, { it[1] })
            .toMutableMap() // k=layer number; v=layer range
    val wait = Walker().waitTimeBeforeCrossing(layers)
    println(wait)
}