#!/usr/bin/env groovy

class TwoFactorAuth {
    static final int WIDTH = 50
    static final int HEIGHT = 6

    def reRect = /rect (.*)x(.*)/
    def reRow = /rotate row y=(.*) by (.*)/
    def reCol = /rotate column x=(.*) by (.*)/

    def printGrid(grid) {
        (0..HEIGHT-1).each { r ->
            (0..WIDTH-1).each { c ->
                print([c, r] in grid ? '#' : '.')
            }
            print "\n"
        }
    }

    def shiftValues = { values, int offset, int modulo ->
        values.collect({ (it + offset) % modulo })
    }

    def lit = [] as Set

    def decode(String[] input) {
        input.each { line ->
            if (line.matches(reRect)) {
                def (_, w, h) = (line =~ reRect)[0]
                def rect = [(0..(w as int)-1), (0..(h as int)-1)].combinations()
                lit.addAll rect
            } else if (line.matches(reRow)) {
                def (_, row, offset) = (line =~ reRow)[0]
                def oldCells = lit.findAll { it[1] == row as int }
                def cols = oldCells.collect {it[0]}
                def shiftedCols = shiftValues(cols, offset as int, WIDTH)
                def newCells = shiftedCols.collect { [it, row as int] }
                lit.removeAll oldCells
                lit.addAll newCells
            } else if (line.matches(reCol)) {
                def (_, col, offset) = (line =~ reCol)[0]
                def oldCells = lit.findAll { it[0] == col as int }
                def rows = oldCells.collect {it[1]}
                def shiftedRows = shiftValues(rows, offset as int, HEIGHT)
                def newCells = shiftedRows.collect { [col as int, it] }
                lit.removeAll oldCells
                lit.addAll newCells
            }
        }
        lit
    }

}

def input = new File('day08.txt') as String[]

def instructions = '''rect 3x2
rotate column x=1 by 1
rotate row y=0 by 4
rotate column x=1 by 1'''.split("\n")

def display = new TwoFactorAuth()
def grid = display.decode(input)
println grid.size()
display.printGrid(grid)