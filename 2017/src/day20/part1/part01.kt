package day20.part1

import java.io.File
import kotlin.math.abs

// Day 20: Particle Swarm
//
// Attempt to run a long simulation.
// That works but it might not be the most elegant or robust solution.
// https://www.reddit.com/r/adventofcode/comments/7l08fv/2017_day_20_trying_to_understand/
// https://www.reddit.com/r/adventofcode/comments/7kzchz/2017_day_20_part_1_metric/

data class Vect(val x: Long, val y: Long, val z: Long) {
    fun add(other: Vect) = Vect(x + other.x, y + other.y, z + other.z)
}

data class Particule(val p: Vect, val v: Vect, val a: Vect) {
    fun move() = Particule(p = p.add(v).add(a), v = v.add(a), a = a)
    fun distanceFromOrigin() = abs(p.x) + abs(p.y) + abs(p.z)
}

fun main(args: Array<String>) {
    var particules = File("src/day20/input.txt").bufferedReader().readLines().map { line ->
        val re = Regex("p=<(.*),(.*),(.*)>, v=<(.*),(.*),(.*)>, a=<(.*),(.*),(.*)>")
        val g = re.matchEntire(line)!!.groupValues.dropWhile { it.toLongOrNull() === null }.map { it.toLong() }
        Particule(p = Vect(g[0], g[1], g[2]), v = Vect(g[3], g[4], g[5]), a = Vect(g[6], g[7], g[8]))
    }

    for (i in 1..5_000) particules = particules.map { it.move() }
    val n = particules.indexOf(particules.minBy { it.distanceFromOrigin() })
    println(n)
}