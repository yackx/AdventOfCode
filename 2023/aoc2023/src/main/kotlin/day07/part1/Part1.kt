package day07.part1

typealias Hand = String
typealias Bid = Int
typealias Game = Pair<Hand, Bid>

val strength: (Game) -> Comparable<*>? = { g ->
    val cardsCount = g.first.groupingBy { it }.eachCount()
    when (cardsCount.values.sortedDescending()) {
        listOf(5) -> 7  // Five of a kind
        listOf(4, 1) -> 6  // Four of a kind
        listOf(3, 2) -> 5  // Full house
        listOf(3, 1, 1) -> 4  // Three of a kind
        listOf(2, 2, 1) -> 3  // Two pairs
        listOf(2, 1, 1, 1) -> 2  // One pair
        listOf(1, 1, 1, 1, 1) -> 1  // High card
        else -> throw AssertionError("Invalid hand: ${g.first}")
    }
}

const val ranks = "23456789TJQKA"

val tieBreaker: (Game) -> Comparable<*>? = { g ->
    g.first
        .map { h -> ranks.indexOf(h).toString(16) }
        .joinToString("")
}

val gameComparator = compareBy(strength).thenBy(tieBreaker)

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
