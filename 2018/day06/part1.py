#!/usr/bin/env python3

import re
from collections import Counter

data = open('input.txt').read().splitlines()
coords = [(x, y) for (x, y) in (map(int, re.findall(r"\d+", line)) for line in data)]

# Perimeter
left = min(n[0] for n in coords)
right = max(n[0] for n in coords)
top = min(n[1] for n in coords)
bottom = max(n[1] for n in coords)

grid = {}   # k=point, v=index of location

# Iterate over each location inside the perimeter +1 (to detect infinite areas).
# Compute its distance to every coordinate
distance = lambda c1, c2: abs(c1[0] - c2[0]) + abs(c1[1] - c2[1])
for x in range(left-1, right+2):
    for y in range(top-1, bottom+2):
        p = (x, y)
        distances = [distance(p, c) for c in coords]
        lowest = min(distances)
        if distances.count(lowest) == 1:
            grid[p] = distances.index(lowest)   # no ex-aequo

# Any area found in the outer edge is an infinite area. Discard it
edges = [
        [(x, top-1) for x in range(left-1, right+1)] +
        [(x, bottom+1) for x in range(left-1, right+1)] +
        [(left-1, y) for y in range(top-1, bottom+1)] +
        [(right+1, y) for y in range(top-1, bottom+1)]
    ]
edges = [item for sublist in edges for item in sublist]  # flatten
infinites = {grid[p] for p in edges if p in grid}

# For each coordinate (k), count how many locations are closest (v)
frequencies = Counter([c for c in grid.values() if c not in infinites])

# Size of the largest finite area
print(max(frequencies.values()))
