#!/usr/bin/env python3

import re
from collections import Counter


def distance(c1, c2):
    return abs(c1[0] - c2[0]) + abs(c1[1] - c2[1])


data = open('input.txt').read().splitlines()
coord = [(x, y) for (x, y) in (map(int, re.findall(r"\d+", line)) for line in data)]
print(coord)
left = min(n[0] for n in coord)
right = max(n[0] for n in coord)
top = min(n[1] for n in coord)
bottom = max(n[1] for n in coord)
print(left, right, top, bottom)
grid = {}

for x in range(left-1, right+2):
    for y in range(top-1, bottom+2):
        p = (x, y)
        distances = [distance(p, c) for c in coord]
        lowest = min(distances)
        grid[p] = distances.index(lowest) if distances.count(lowest) == 1 else -1
        # print(f"p: {p} distances {distances} lowest={lowest} means {grid[p]} is closest")

# print("GRID", grid)

# Infinites
# topEdge = [(x, top-1) for x in range(left-1, right+1)]
# bottomEdge = [(x, bottom+1) for x in range(left-1, right+1)]
# leftEdge = [(left-1, y) for y in range(top-1, bottom+1)]
# rightEdge = [(right+1, y) for y in range(top-1, bottom+1)]
# print(topEdge)
# print(bottomEdge)
# print(leftEdge)
# print(rightEdge)

leftEdge = [(left-1, y) for y in range(top-1, bottom+1)]
edges = [
        [(x, top-1) for x in range(left-1, right+1)] +
        [(x, bottom+1) for x in range(left-1, right+1)] +
        [(left-1, y) for y in range(top-1, bottom+1)] +
        [(right+1, y) for y in range(top-1, bottom+1)]
    ]
edges = [item for sublist in edges for item in sublist]
# print(edges)
infinites = {grid[p] for p in edges if grid[p] is not -1}
print("INF", infinites)
frequencies = Counter([v for v in grid.values() if v is not -1 and v not in infinites])
print("FREQ", frequencies)
print(max([f for f in frequencies.values()]))

