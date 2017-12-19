package day18

import java.io.File

// Day 18: Duet

fun registerOrOperand(registers: Map<String, Long>, operand: String) =
    operand.toLongOrNull() ?: registers.getValue(operand)

fun main(args: Array<String>) {
    val instructions = File("day18/input.txt").bufferedReader().readLines()

    val registers = mutableMapOf<String, Long>().withDefault { 0 }
    var ip = 0
    var lastSound = 0L
    var done = false
    val re = Regex("(\\w{3}) ?(\\w)? ?(.*)?")
    while (!done) {
        println("${instructions[ip]} ip=$ip reg=$registers ($lastSound)")
        val (instruction, op1, op2) = re.matchEntire(instructions[ip])!!.destructured
        val convOp2 = registerOrOperand(registers, op2)
        when (instruction) {
            "snd" -> lastSound = registers.getValue(op1)
            "set" -> registers[op1] = convOp2
            "add" -> registers[op1] = registers.getValue(op1) + convOp2
            "mul" -> registers[op1] = registers.getValue(op1) * convOp2
            "mod" -> registers[op1] = registers.getValue(op1) % convOp2
            "rcv" -> if (registers.getValue(op1) != 0L) {
                registers[op1] = lastSound
                done = true
            }
            "jgz" -> if (registerOrOperand(registers, op1) > 0) ip += op2.toInt() - 1
            else -> throw IllegalArgumentException(instruction)
        }
        ip++
    }

    println(lastSound)
}