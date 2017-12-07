package day07

import java.io.File

data class Info(val weight: Int, val children: List<String>)

private fun loadProgramsInfo(fileName: String): Map<String, Info> {
    val re = Regex("(.*) \\((\\d*)\\)( -> )?(.*)?")
    val map = mutableMapOf<String, Info>()
    File(fileName).bufferedReader().lines().forEach { line ->
        val (program, weight, _, childrenStr) = re.matchEntire(line)!!.destructured
        val children = if (childrenStr.isNotEmpty()) childrenStr.split(", ") else listOf()
        map.put(program, Info(weight.toInt(), children))
    }
    return map
}

private fun findRoot(programsInfo: Map<String, Info>): String {
    val notRoot = mutableSetOf<String>()
    val couldBeRoot = mutableSetOf<String>()
    programsInfo.forEach { p, i ->
        if (!i.children.isEmpty()) {
            notRoot.addAll(i.children)
            couldBeRoot.removeAll(i.children)
        }
        if (p !in notRoot) {
            couldBeRoot.add(p)
        }
    }
    if (couldBeRoot.size != 1) throw IllegalStateException("root candidates: $couldBeRoot")
    return couldBeRoot.first()
}

private fun findUnbalanced(programsInfos: Map<String, Info>) {
    val root = findRoot(programsInfos)
    weight(root, programsInfos)
}

private fun weight(node: String, programsInfos: Map<String, Info>): Int {
    val children = programsInfos[node]!!.children
    if (children.isEmpty()) {
        return programsInfos[node]!!.weight
    }

    val childrenWeights = children.associateBy({it}, { weight(it, programsInfos)})

    val counts = childrenWeights.values.groupingBy { it }.eachCount()
    if (counts.size > 1) {
        val unbalancedWeight = counts.minBy { it.value }!!.key
        val balancedWeight = counts.maxBy { it.value }!!.key
        val offender = childrenWeights.filter { it.value ==  unbalancedWeight }.keys.first()
        val diff = programsInfos[offender]!!.weight + balancedWeight - unbalancedWeight
        println(diff)
        System.exit(0)
    }

    return childrenWeights.values.sum() + programsInfos[node]!!.weight
}

fun main(args: Array<String>) {
    val programsInfos = loadProgramsInfo("day07/input.txt")
    findUnbalanced(programsInfos)
}