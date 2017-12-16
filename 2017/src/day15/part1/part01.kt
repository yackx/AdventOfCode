package day15.part1

fun main(args: Array<String>) {
    // Puzzle input
    var genA = 634L
    var genB = 301L

    val factorA = 16807L
    val factorB = 48271L
    val mod = 2147483647L
    val bitmask = 0xFFFFL

    val score = (0..40_000_000).count {
        genA = (genA * factorA) % mod
        genB = (genB * factorB) % mod
        genA and bitmask == genB and bitmask
    }
    println(score)
}