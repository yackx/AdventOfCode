package day20.part2

import java.io.File

// Day 20: Particle Swarm

data class Vect(val x: Long, val y: Long, val z: Long) {
    fun add(other: Vect) = Vect(x + other.x, y + other.y, z + other.z)
}

data class Particule(val p: Vect, val v: Vect, val a: Vect) {
    fun move() = Particule(p = p.add(v).add(a), v = v.add(a), a = a)
}

fun main(args: Array<String>) {
    var particules = File("day20/input.txt").bufferedReader().readLines().map { line ->
        val re = Regex("p=<(.*),(.*),(.*)>, v=<(.*),(.*),(.*)>, a=<(.*),(.*),(.*)>")
        val g = re.matchEntire(line)!!.groupValues.dropWhile { it.toLongOrNull() === null }.map { it.toLong() }
        Particule(p = Vect(g[0], g[1], g[2]), v = Vect(g[3], g[4], g[5]), a = Vect(g[6], g[7], g[8]))
    }

    for (i in 1..5_000) {
        particules = particules.map { it.move() }
        val collidingPs = particules.groupingBy { it.p }.eachCount().filter { it.value > 1 }.keys
        particules = particules.filter { it.p !in collidingPs }
    }

    println(particules.size)
}