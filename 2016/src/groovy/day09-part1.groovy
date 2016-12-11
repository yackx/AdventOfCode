#!/usr/bin/env groovy

def re = /\((.+?)x(.+?)\)/

def decompressedLength = { String input ->
    int decompressed = 0
    input = input.replaceAll("\n", '')

    while (input) {
        def group = input =~ re
        if (group) {
            def captured = group[0][0] as String
            def howMany = group[0][1] as int
            def repeat = group[0][2] as int
            decompressed += repeat * howMany
            input = input.substring(captured.size() + howMany)
        } else {
            decompressed++
            input = input.substring(1)
        }
    }

    decompressed
}

def input = new File('day09.txt').text
println decompressedLength(input)


assert decompressedLength('ADVENT') == 6
assert decompressedLength('A(1x5)BC') == 7
assert decompressedLength('(3x3)XYZ') == 9
assert decompressedLength('A(2x2)BCD(2x2)EFG') == 11
assert decompressedLength('(6x1)(1x3)A') == 6
assert decompressedLength('X(8x2)(3x3)ABCY') == 18

