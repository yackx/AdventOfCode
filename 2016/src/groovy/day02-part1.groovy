#!/usr/bin/env groovy

def keypad = [
        [-1, -1]: 1, [0, -1]: 2, [1, -1]: 3,
        [-1, 0] : 4, [0, 0] : 5, [1, 0] : 6,
        [-1, 1] : 7, [0, 1] : 8, [1, 1] : 9
]

int x = 0, y = 0
String code = ''

def input = new File('day2.txt') as String[]

input.each { line ->
    line.each { d ->
        int px = x, py = y

        switch (d) {
            case 'U': y -= 1; break
            case 'L': x -= 1; break
            case 'D': y += 1; break
            case 'R': x += 1; break
        }

        if (!([x, y] in keypad.keySet())) {
            // invalid move, go back
            x = px
            y = py
        }
    }

    code += keypad[[x, y]] as String
}

println code