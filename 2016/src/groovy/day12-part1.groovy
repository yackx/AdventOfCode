#!/usr/bin/env groovy

class Assembunny {
    def registers = [:].withDefault { 0 }
    int pointer = 0

    def funcs = [
        'cpy': { String op1, String op2 ->
            registers[op2] = op1.isInteger() ? op1 as int : registers[op1]
            pointer++
        },
        'inc': { reg ->
            registers[reg]++
            pointer++
        },
        'dec': { reg ->
            registers[reg]--
            pointer++
        },
        'jnz': { String op1, String offset ->
            // op1 can also be numerical
            boolean zero = op1.isInteger() ? op1.toInteger() == 0 : registers[op1] == 0
            if (!zero) pointer += offset as int
            else pointer++
        }
    ]

    def execute(instructions) {
        while (pointer < instructions.size()) {
            def splitted = instructions[pointer].split(' ')
            def operator = splitted[0]
            def operands = splitted[1..-1]
            funcs[operator](*operands)
       }
    }
}

def input = new File('day12.txt') as String[]
def bunny = new Assembunny()
bunny.execute(input)
println bunny.registers['a']


bunny = new Assembunny()
bunny.execute(['cpy 1 a'])
assert bunny.registers['a'] == 1

bunny = new Assembunny()
bunny.execute(['cpy 1 a', 'cpy a b'])
assert bunny.registers['a'] == 1
assert bunny.registers['b'] == 1

bunny = new Assembunny()
bunny.execute(['inc c'])
assert bunny.registers['c'] == 1
assert bunny.registers['a'] == 0

bunny = new Assembunny()
bunny.execute(['inc c', 'dec c'])
assert bunny.registers['c'] == 0

bunny = new Assembunny()
bunny.execute(['cpy 41 a', 'inc a', 'inc a', 'dec a', 'jnz a 2', 'dec a'])
assert bunny.registers['a'] == 42

bunny = new Assembunny()
bunny.execute(['inc a', 'jnz a 2', 'inc a', 'inc b'])
assert bunny.registers['a'] == 1
assert bunny.registers['b'] == 1

bunny = new Assembunny()
bunny.execute(['cpy -10 a', 'inc a', 'jnz a -1', 'inc a', 'inc b'])
assert bunny.registers['a'] == 1
assert bunny.registers['b'] == 1

bunny = new Assembunny()
bunny.execute(['inc a', 'jnz a 20', 'inc a', 'inc b'])
assert bunny.registers['a'] == 1
assert bunny.registers['b'] == 0
