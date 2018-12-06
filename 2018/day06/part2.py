#!/usr/bin/env python3

import re

safe = 10000


def distance(c1, c2):
    return abs(c1[0] - c2[0]) + abs(c1[1] - c2[1])


def is_safe_spot(p):
    sum_of_distances = sum([distance(p, c) for c in coords])
    return sum_of_distances < safe


data = open('input.txt').read().splitlines()
coords = [(x, y) for (x, y) in (map(int, re.findall(r"\d+", line)) for line in data)]

# Find the perimeter
left = min(n[0] for n in coords)
right = max(n[0] for n in coords)
top = min(n[1] for n in coords)
bottom = max(n[1] for n in coords)


locations = ((x, y) for x in range(left, right) for y in range(top, bottom))
print(len([p for p in locations if is_safe_spot(p)]))
