package day07

import java.io.File

fun main(args: Array<String>) {
    val notRoot = mutableSetOf<String>()
    val couldBeRoot = mutableSetOf<String>()
    val re = Regex("(.*) \\(.*\\)( -> )?(.*)?")
    File("day07/input.txt").bufferedReader().lines().forEach { line ->
        val children = re.matchEntire(line)!!.groupValues[3].split(", ")
        println("CHILDREN $children")
        notRoot.addAll(children)
        couldBeRoot.removeAll(children)
        val program = re.matchEntire(line)!!.groupValues[1]
        if (program !in notRoot) couldBeRoot.add(program)
        println(line)
        println("Could be root: $couldBeRoot")
        println("notRoot: $notRoot")
        println()
    }
    if (couldBeRoot.size != 1) throw IllegalStateException("root candidates: $couldBeRoot")
    println(couldBeRoot.first())
}