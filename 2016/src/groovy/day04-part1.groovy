#!/usr/bin/env groovy

def re = /(.+)-(\d+)\[(.+)\]/

int sum = 0
def input = new File('day04.txt') as String[]
input.each { line ->
    def (_, room, sector, checksum) = (line =~ re)[0]
    room = room.replaceAll('-', '')
    def digest = room               // 'aaaaa-bbb-z-y-x'
            .toList()               // ['a', 'a', ... 'y', 'x'
            .unique()               // ['a', 'b', 'z', 'y', 'x']
                                    // ['a': 5, 'b': 3, ...]
            .collectEntries { [(it): room.count(it)] }
                                    // ['a': 5, 'b': 3, 'x': 1, ...]
            .sort { a, b -> b.value <=> a.value ?: a.key <=> b.key }
            .take(5)
            .keySet()               // ['a', 'b', 'x', 'y', 'z']
            .join()                 // 'abxyz'
    if (digest == checksum) sum += sector as int
}

println sum
