#!/usr/bin/env groovy

def input = new File('day03.txt') as String[]

int possible = 0

input.each { dims ->
    def (a, b, c) = dims.split()*.toInteger()
    if (a + b > c && a + c > b && b + c > a) possible++
}

println possible