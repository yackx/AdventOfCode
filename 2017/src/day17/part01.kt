package day17

fun main(args: Array<String>) {
    var buffer = mutableListOf(0)
    val steps = 376
    var index = 0
    for (i in 1..2017) {
        index = (index + steps) % i
        val l = buffer.toList()
        buffer = l.subList(0, index + 1).toMutableList()
        buffer.add(i)
        buffer.addAll(l.subList(index + 1, l.size).toMutableList())
        index++
    }
    println(buffer[buffer.indexOf(2017) + 1])
}