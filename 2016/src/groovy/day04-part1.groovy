#!/usr/bin/env groovy

def re = /(.+)-(\d+)\[(.+)\]/

int sum = 0
def input = new File('day04.txt') as String[]
input.each { line ->
    def (_, room, sector, checksum) = (line =~ re)[0]
    room = room.replaceAll('-', '')
    println "$room $sector $checksum"
    def letters = room.toList().unique()
    println letters
    def m = letters.collectEntries { [(it): room.count(it)] }
    println m
    def foo = m.sort { a, b -> b.value <=> a.value ?: a.key <=> b.key }
    def bar = foo.take(5).keySet().join()
    println bar
    if (bar == checksum) sum += sector as int
    println()
}

println sum
