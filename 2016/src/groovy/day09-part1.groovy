#!/usr/bin/env groovy

def re = /\((.+?)x(.+?)\)/

def decompressedLength = { String input ->
    boolean inMarker = false
    int decompressed = 0
    int index = 0
    while (index != input.length() && input[index] != "\n") {
        if (!inMarker) {
            if (!(input[index] in ['(', "\n"])) {
                decompressed++
                index++
            } else {
                inMarker = true
            }
        } else {
            def sub = input.substring(index)
            def group = sub =~ re
            def captured = group[0][0] as String
            def howMany = group[0][1] as int
            def repeat = group[0][2] as int
            decompressed += repeat * howMany
            index += captured.size() + howMany
            inMarker = false
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

