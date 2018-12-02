#!/usr/bin/env python3

from collections import Counter

with open('input.txt') as file:
    data = file.read().splitlines()

two = 0
three = 0
for box in data:
    frequencies = Counter(box)
    check = lambda n: n in frequencies.values()
    two += 1 if check(2) else 0
    three += 1 if check(3) else 0
print(two * three)
