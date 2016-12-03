#!/usr/bin/env groovy

def directions = [ 'N', 'E', 'S', 'W' ]

int x = 0, y = 0
int dir = 0

def input = new File('day1.txt').text.split(', ')

input.each { d ->
    String turn = d[0]
    int steps = d[1..-1] as int

    dir += turn == 'L' ? -1 : 1
    dir = ((dir % 4) + 4 ) % 4

    switch (directions[dir]) {
        case 'N': y -= steps; break
        case 'W': x -= steps; break
        case 'S': y += steps; break
        case 'E': x += steps; break
    }
}

println Math.abs(x) + Math.abs(y)
