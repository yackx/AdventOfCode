#!/usr/bin/env groovy

def re = /(.+)-(\d+)\[(.+)\]/

def shiftChar = { char c, int n ->
    if (c == ' ' as char) return c
    char a = 'a'
    def i = (c - a + n) % 26
    (i + a) as char
}

def input = new File('day04.txt') as String[]
for (line in input) {
    def (_, room, sector) = (line =~ re)[0]

    def deciphered = room.replaceAll('-', ' ')
            .toCharArray()
            .collect { shiftChar(it, sector as int) }
            .join()

    if (deciphered == 'northpole object storage') {
        println sector
        break
    }
}
