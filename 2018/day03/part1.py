#!/usr/bin/env python3

import re

patches = dict()
data = open('input.txt').read().splitlines()
claims = [list(map(int, re.findall(r"\d+", line))) for line in data]
for claim in claims:
    _, left, top, width, height = claim
    p = lambda l, t, w, h: [(i, j) for i in range(l, l+w) for j in range(t, t+h)]
    for patch in p(left, top, width, height):
        patches[patch] = patches.get(patch, 0) + 1

print(len([n for n in patches.values() if n > 1]))
