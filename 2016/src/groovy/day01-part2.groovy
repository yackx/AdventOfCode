#!/usr/bin/env groovy

def directions = [ 'N', 'E', 'S', 'W' ]

int x = 0, y = 0
int dir = 0

visited = [[x, y]]

def addLocation = { int px, int py ->
    if ([x, y] in visited) return true
    visited << [x, y]
    return false
}

def input = new File('day1.txt').text.split(', ')

for (d in input) {
    String turn = d[0]
    int steps = d[1..-1] as int

    dir += turn == 'L' ? -1 : 1
    dir = ((dir % 4) + 4 ) % 4

    for (int i = 0; i < steps; i++) {
        switch (directions[dir]) {
            case 'N': y -= 1; break
            case 'W': x -= 1; break
            case 'S': y += 1; break
            case 'E': x += 1; break
        }
        if (addLocation(x, y)) {
            println Math.abs(x) + Math.abs(y)
            System.exit(0)
        }
    }

}
