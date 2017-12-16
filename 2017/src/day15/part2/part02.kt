package day15.part2

fun main(args: Array<String>) {
    // Puzzle input
    val genA = 65L
    val genB = 8921L

    val factorA = 16807L
    val factorB = 48271L
    val mod = 2147483647L
    val bitmask = 0xFFFFL

    val seqA = generateSequence(genA) { it ->
        var a = it
        do {
            a = (a * factorA) % mod
        } while (a % 4 != 0L)
        a
    }.iterator()
    seqA.next()

    val seqB = generateSequence(genB) { it ->
        var b = it
        do {
            b = (b * factorB) % mod
        } while (b % 8 != 0L)
        b
    }.iterator()
    seqB.next()

    val score = (1..5_000_000).count {
        val a = seqA.next()
        val b = seqB.next()
        a and bitmask == b and bitmask
    }
    println(score)
}