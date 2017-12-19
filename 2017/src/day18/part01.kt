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
        val (instruction, op1Str, op2Str) = re.matchEntire(instructions[ip])!!.destructured
        val convOp2 = registerOrOperand(registers, op2Str)
        val convOp1 = registerOrOperand(registers, op1Str)
        when (instruction) {
            "snd" -> lastSound = convOp1
            "set" -> registers[op1Str] = convOp2
            "add" -> registers[op1Str] = convOp1 + convOp2
            "mul" -> registers[op1Str] = convOp1 * convOp2
            "mod" -> registers[op1Str] = convOp1 % convOp2
            "rcv" -> if (convOp1 != 0L) {
                registers[op1Str] = lastSound
                done = true
            }
            "jgz" -> if (convOp1 > 0) ip += op2Str.toInt() - 1
            else -> throw IllegalArgumentException(instruction)
        }
        ip++
    }

    println(lastSound)
}