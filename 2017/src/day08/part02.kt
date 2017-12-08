package day08

import java.io.File

fun main(args: Array<String>) {
    val registers = mutableMapOf<String, Int>().withDefault { 0 }
    val re = Regex("(\\w.*) (\\w.*) (.*) if (\\w.*) (.*) (.*)")
    var highest = Int.MIN_VALUE

    File("day08/input.txt").bufferedReader().lines().forEach { line ->
        val (register, operator, operandStr, conditionRegister, conditionOperator, conditionValueStr) =
                re.matchEntire(line)!!.destructured
        val conditionValue = conditionValueStr.toInt()

        // Check condition
        val conditionRegisterValue = registers.getValue(conditionRegister)
        val conditionMet = when (conditionOperator) {
            ">" -> conditionRegisterValue > conditionValue
            "<" -> conditionRegisterValue < conditionValue
            ">=" -> conditionRegisterValue >= conditionValue
            "<=" -> conditionRegisterValue <= conditionValue
            "==" -> conditionRegisterValue == conditionValue
            "!=" -> conditionRegisterValue != conditionValue
            else -> throw IllegalArgumentException("unknown conditionOperator: $conditionOperator")
        }
        if (conditionMet) {
            val operand = operandStr.toInt()
            val value = when (operator) {
                "inc" -> operand
                "dec" -> -operand
                else -> throw IllegalArgumentException("unknown operator $operator")
            }
            registers[register] = registers.getValue(register) + value
            val current = registers[register]!!
            if (current > highest) highest = current
        }
    }

    println(highest)
}