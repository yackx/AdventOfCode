package day18

import java.io.File

// Day 18: Duet

class Program(private val id: Int, private val instructions: List<String>) {
    var blocked = false
        private set

    var sendCounter = 0
        private set

    val q = mutableListOf<Long>()
    lateinit var otherQueue: MutableList<Long>

    private var ip = 0
    private val registers = mutableMapOf<String, Long>().withDefault { 0 }

    private fun registerOrOperand(registers: Map<String, Long>, operand: String) =
        operand.toLongOrNull() ?: registers.getValue(operand)

    fun next() {
        if (!q.isEmpty()) blocked = false               // unblock
        if (blockedOrTerminated()) return               // do nothing if blocked or done

        // Process instructions
        val re = Regex("(\\w{3}) ?(\\w)? ?(.*)?")
        val (instruction, op1Str, op2Str) = re.matchEntire(instructions[ip])!!.destructured
        val convOp2 = registerOrOperand(registers, op2Str)
        val convOp1 = registerOrOperand(registers, op1Str)
        when (instruction) {
            "snd" -> {
                otherQueue.add(convOp1)
                sendCounter++
            }
            "set" -> registers[op1Str] = convOp2
            "add" -> registers[op1Str] = convOp1 + convOp2
            "mul" -> registers[op1Str] = convOp1 * convOp2
            "mod" -> registers[op1Str] = convOp1 % convOp2
            "rcv" ->  {
                if (q.isEmpty()) {
                    blocked = true
                    return
                }
                registers[op1Str] = q.removeAt(0)
            }
            "jgz" -> if (convOp1 > 0) ip += convOp2.toInt() - 1
            else -> throw IllegalArgumentException(instruction)
        }
        ip++
    }

    fun setRegister(reg: String, value: Long) { registers[reg] = value }

    fun blockedOrTerminated() = blocked || ip >= instructions.size

    override fun toString(): String {
        return "Program $id blocked=$blocked ip=$ip done=${blockedOrTerminated()}"
    }
}

fun main(args: Array<String>) {
    val instructions = File("src/day18/input.txt").bufferedReader().readLines()
    val program0 = Program(0, instructions)
    val program1 = Program(1, instructions)
    program1.setRegister("p", 1)
    program0.otherQueue = program1.q
    program1.otherQueue = program0.q
    while (!program0.blockedOrTerminated() || !program1.blockedOrTerminated()) {
        program0.next()
        program1.next()
    }
    println(program1.sendCounter)
}