#!/usr/bin/env python3

from collections import Counter

with open('input.txt') as file:
    data = file.read().splitlines()

two = 0
three = 0
for box in data:
    frequencies = Counter(box)
    count = lambda n: len([f for f in frequencies.values() if f == n])
    two += 1 if count(2) else 0
    three += 1 if count(3) else 0
print(two * three)
