#!/usr/bin/env python3

from itertools import product

with open('input.txt') as file:
    data = file.read().splitlines()

pairs = [p for p in list(product(data, data)) if p[0] < p[1]]

for pair in pairs:
    common = [pair[0][x] for x in range(len(pair[0])) if pair[0][x] == pair[1][x]]
    if len(common) == len(pair[0]) - 1:
        print(''.join(common))
        break
