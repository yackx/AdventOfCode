package day07

import java.io.File

fun main(args: Array<String>) {
    val notRoot = mutableSetOf<String>()
    val couldBeRoot = mutableSetOf<String>()
    val re = Regex("(.*) \\(\\d*\\)( -> )?(.*)?")
    File("day07/input.txt").bufferedReader().lines().forEach { line ->
        val (candidate, _, childrenList, _) = re.matchEntire(line)!!.destructured
        if (!childrenList.isEmpty()) {
            val children = childrenList.split(", ")
            notRoot.addAll(children)
            couldBeRoot.removeAll(children)
        }
        if (candidate !in notRoot) {
            couldBeRoot.add(candidate)
        }
    }
    if (couldBeRoot.size != 1) throw IllegalStateException("root candidates: $couldBeRoot")
    println(couldBeRoot.first())
}