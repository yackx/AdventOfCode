#!/usr/bin/env groovy

def input = new File('day06.txt') as String[]

def chars = (0..input[0].length()-1).collect({ n ->
    def row = input*.charAt(n)
    return row.collectEntries {[ (it): row.count(it) ]}.max {e -> e.value}.key
})

println chars.join()

