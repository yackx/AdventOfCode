#!/usr/bin/env groovy

def input = new File('day03.txt') as String[]

println input.count { dims ->
    def (a, b, c) = dims.split()*.toInteger()
    (a + b > c && a + c > b && b + c > a)
}
