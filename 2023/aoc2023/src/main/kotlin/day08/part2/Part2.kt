package day08.part2


fun solve(instructions: String, nodes: Map<String, Pair<String, String>>): Int {
    val iterators = nodes.filter { it.key.endsWith('A') }
        .map { node -> ghost(node.key, instructions, nodes) }
        .map { it.iterator() }

    var found = false
    var steps = 0
    while (!found) {
        val next = iterators.map { it.next() }
        found = next.all { it.endsWith('Z') }
        steps++
    }

    return steps
}

fun ghost(node: String, instructions: String, nodes: Map<String, Pair<String, String>>): Sequence<String> {
    var instructionIndex = 0
    var currentNode = node
    return generateSequence {
        val (left, right) = nodes[node]!!
        val instruction = instructions[instructionIndex]
        when (instruction) {
            'L' -> currentNode = left
            'R' -> currentNode = right
        }
        instructionIndex = (instructionIndex + 1) % instructions.length
        currentNode
    }
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