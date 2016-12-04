#!/usr/bin/env groovy

def input = new File('day03.txt') as String[]

int possible = 0

def emptyTriangles = { (1..3).collect { [] } }

def triangles = emptyTriangles()
input.each { line ->
    def dims = line.split()*.toInteger()
    dims.eachWithIndex { d, i -> triangles[i] << d }
    if (triangles.first().size() == 3) {
        possible += triangles.count { a, b, c ->
            println "$a $b $c"
            (a + b > c && a + c > b && b + c > a)
        }
        triangles = emptyTriangles()
    }
}

println possible