#!/usr/bin/env python3

from collections import Counter

with open('input.txt') as file:
    data = file.read().splitlines()

two = 0
three = 0
for box in data:
    frequencies = Counter(box)
    incrementFor = lambda n: 1 if len([f for f in frequencies.values() if f == n]) else 0
    two += incrementFor(2)
    three += incrementFor(3)
print(two * three)
