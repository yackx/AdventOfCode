package day07.part1

typealias Hand = String
typealias Bid = Int
typealias Game = Pair<Hand, Bid>

val cardValues = mapOf(
    'A' to 14,
    'K' to 13,
    'Q' to 12,
    'J' to 11,
    'T' to 10,
).plus((2..9).associateBy { it.toString()[0] })

val gameComparator = compareBy<Game> { g -> g.first.toSet().size }
    .thenBy { g ->
        g.first
            .map { cardValues[it]!! }
            .reduce { acc, i -> acc * 16 + i }
    }

fun solve(games: List<Game>): Int =
    games
        .sortedWith(gameComparator)
        .map { it.second }
        .reduceIndexed { index, acc, bid -> acc + bid * (index + 1) }

fun parse(lines: List<String>): List<Pair<Hand, Bid>> =
    lines.map { line ->
        val (hand, bidStr) = line.split(" ")
        hand to bidStr.toInt()
    }

fun main() {
    val lines = generateSequence { readlnOrNull() }
    println(solve(parse(lines.toList())))
}
