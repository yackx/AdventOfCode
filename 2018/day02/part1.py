#!/usr/bin/env python3

from collections import Counter

with open('input.txt') as file:
    data = file.read().splitlines()

two = 0
three = 0
for box in data:
    frequencies = Counter(box)
    counter = lambda n: len([f for f in frequencies.values() if f == n])
    two = two + 1 if counter(2) else two
    three = three + 1 if counter(3) else three
print(two * three)
