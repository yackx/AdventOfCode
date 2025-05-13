package day08.part1

fun solve(instructions: String, nodes: Map<String, Pair<String, String>>): Int {
    var node = "AAA"
    var instructionIndex = 0
    var steps = 0
    while (node != "ZZZ") {
        val (left, right) = nodes[node]!!
        val instruction = instructions[instructionIndex]
        when (instruction) {
            'L' -> node = left
            'R' -> node = right
        }
        instructionIndex = (instructionIndex + 1) % instructions.length
        steps++
    }
    return steps
}

fun parse(lines: List<String>): Pair<String, Map<String, Pair<String, String>>> {
    val instructions = lines.first()
    val nodes = lines.drop(2).map { line ->
        val re = Regex("(\\w+) = \\((\\w+), (\\w+)\\)")
        val (node, left, right) = re.matchEntire(line)!!.destructured
        node to (left to right)
    }
    return instructions to nodes.toMap()
}

fun main() {
    val lines = generateSequence { readlnOrNull() }
    val (instructions, nodes) = parse(lines.toList())
    println(solve(instructions, nodes))
}