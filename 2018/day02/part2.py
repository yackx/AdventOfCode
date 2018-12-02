#!/usr/bin/env python3

from itertools import product

with open('input.txt') as file:
    data = file.read().splitlines()

pairs = [p for p in list(product(data, data)) if p[0] < p[1]]

for p1, p2 in pairs:
    common = [p1[x] for x in range(len(p1)) if p1[x] == p2[x]]
    if len(common) == len(p1) - 1:
        print(''.join(common))
        break
