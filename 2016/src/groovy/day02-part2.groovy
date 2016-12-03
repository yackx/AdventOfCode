#!/usr/bin/env groovy

def keypad = [
                                     [0, -2]: '1',
                      [-1, -1]: '2', [0, -1]: '3', [1, -1]: '4',
        [-2, 0]: '5', [-1, 0] : '6', [0, 0] : '7', [1, 0] : '8', [2, 0]: '9',
                      [-1, 1] : 'A', [0, 1] : 'B', [1, 1] : 'C',
                                     [0, 2] : 'D'
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

    code += keypad[[x, y]]
}

println code