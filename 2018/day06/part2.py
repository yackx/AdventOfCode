#!/usr/bin/env python3

import re
from collections import Counter


def distance(c1, c2):
    return abs(c1[0] - c2[0]) + abs(c1[1] - c2[1])


safe = 10000

data = open('input.txt').read().splitlines()
coords = [(x, y) for (x, y) in (map(int, re.findall(r"\d+", line)) for line in data)]

# Find the perimeter
left = min(n[0] for n in coords)
right = max(n[0] for n in coords)
top = min(n[1] for n in coords)
bottom = max(n[1] for n in coords)

grid = {}   # k=point, v=index of location
count = 0

for x in range(left-1, right+2):
    for y in range(top-1, bottom+2):
        p = (x, y)
        sum_of_distances = sum([distance(p, c) for c in coords])
        if sum_of_distances < safe:
            count += 1


print(count)
