package day04.part1

typealias Numbers = List<Long>
typealias Card = Pair<Numbers, Numbers>

fun parse(lines: List<String>): List<Card> =
    lines.map { line ->
        val numbers: (String) -> Numbers = { part ->
            Regex("\\d+")
                .findAll(part)
                .map { n -> n.value.toLong() }
                .toList()
        }
        val content = line.split(":")[1].split("|")
        numbers(content[0]) to numbers(content[1])
    }

fun solve(cards: List<Card>): Long =
    cards.map { card ->
        val (winning, played) = card
        val count = winning.intersect(played).size
        1.shl(count-1).toLong()
    }.sum()

fun main() {
    val cards = parse(generateSequence { readlnOrNull() }.toList())
    val result = solve(cards)
    println(result)
}