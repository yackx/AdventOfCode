#!/usr/bin/env python3

from collections import Counter

with open('input.txt') as f:
    data = f.read().splitlines()

two = 0
three = 0
for box in data:
    freq = Counter(box)
    if len([f for f in freq.values() if f == 2]) > 0:
        two = two + 1
    if len([f for f in freq.values() if f == 3]) > 0:
        three = three + 1
print(two*three)
