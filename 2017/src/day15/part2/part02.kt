package day15.part2

fun generateIterator(gen: Long, factor: Long, divisor: Long): Iterator<Long> {
    val mod = 2147483647L

    val seq = generateSequence(gen) { it ->
        var n = it
        do {
            n = (n * factor) % mod
        } while (n % divisor != 0L)
        n
    }.iterator()
    seq.next()  // skip generator itself
    return seq
}

fun main(args: Array<String>) {
    // Puzzle input
    val genA = 65L
    val genB = 8921L

    val factorA = 16807L
    val factorB = 48271L
    val bitmask = 0xFFFFL

    val seqA = generateIterator(gen = genA, factor = factorA, divisor = 4)
    val seqB = generateIterator(gen = genB, factor = factorB, divisor = 8)

    val score = (1..5_000_000).count {
        seqA.next() and bitmask == seqB.next() and bitmask
    }
    println(score)
}