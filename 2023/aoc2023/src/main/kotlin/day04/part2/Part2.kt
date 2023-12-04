package day04.part2

typealias Numbers = List<Int>
typealias Card = Pair<Numbers, Numbers>

fun solve(cards: List<Card>): Int {
    val originalAndCopies = mutableMapOf<Int, Int>()
    IntRange(1, cards.size).forEach { i -> originalAndCopies[i] = 1 }
    cards.forEachIndexed { index, card ->
        val cardNumber = index + 1
        val (winning, played) = card
        val matchingNumbers = winning.intersect(played).size
        val howMany = originalAndCopies.getValue(cardNumber)
        val start = cardNumber + 1
        val end = (cardNumber + matchingNumbers).coerceAtMost(cards.size+1)
        IntRange(start, end).forEach { i ->
            originalAndCopies[i] = originalAndCopies.getValue(i) + howMany
        }
    }
    return originalAndCopies.values.sum()
}

fun parse(lines: List<String>): List<Card> =
    lines.map { line ->
        val numbers: (String) -> Numbers = { part ->
            Regex("\\d+")
                .findAll(part)
                .map { n -> n.value.toInt() }
                .toList()
        }
        val content = line.split(":")[1].split("|")
        numbers(content[0]) to numbers(content[1])
    }

fun main() {
    val cards = parse(generateSequence { readlnOrNull() }.toList())
    val result = solve(cards)
    println(result)
}