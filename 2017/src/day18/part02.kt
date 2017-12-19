package day18

import java.io.File

// Day 18: Duet

class Program(val instructions: List<String>) {
    private var ip = 0
    private var lastSound = 0L
    private val registers = mutableMapOf<String, Long>().withDefault { 0 }
    private var done = false

    private fun registerOrOperand(registers: Map<String, Long>, operand: String) =
        operand.toLongOrNull() ?: registers.getValue(operand)

    fun next() {
        val re = Regex("(\\w{3}) ?(\\w)? ?(.*)?")
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
            "jgz" -> if (convOp1 > 0) ip += convOp2.toInt() - 1
            else -> throw IllegalArgumentException(instruction)
        }
        ip++
    }

    fun isDone() = done
    fun lastSound() = lastSound
}

fun main(args: Array<String>) {
    val instructions = File("day18/input.txt").bufferedReader().readLines()
    val program = Program(instructions)
    while (!program.isDone()) {
        program.next()
    }
    println(program.lastSound())
}