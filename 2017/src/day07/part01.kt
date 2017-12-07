package day07

import java.io.File

fun main(args: Array<String>) {
    val notRoot = mutableSetOf<String>()
    val couldBeRoot = mutableSetOf<String>()
    val arrow = Regex("(.*) \\(.*\\) -> (.*)")
    File("day07/input.txt").bufferedReader().lines().forEach { line ->
        if (line.matches(arrow)) {
            val children = arrow.matchEntire(line)!!.groupValues[2].split(", ")
            notRoot.addAll(children)
            couldBeRoot.removeAll(children)
            val program = arrow.matchEntire(line)!!.groupValues[1]
            if (program !in notRoot) couldBeRoot.add(program)
        } else {
            val program = Regex("(.*) \\(.*\\).*").matchEntire(line)!!.groupValues[1]
            if (program !in notRoot) couldBeRoot.add(program)
        }
        println(line)
        println("Could be root: $couldBeRoot")
        println("notRoot: $notRoot")
        println()
    }
    if (couldBeRoot.size != 1) throw IllegalStateException("root candidates: $couldBeRoot")
    println(couldBeRoot.first())
}