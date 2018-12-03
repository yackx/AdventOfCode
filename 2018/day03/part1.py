#!/usr/bin/env python3

import re

patches = dict()
data = open('input.txt').read().splitlines()
claims = [list(map(int, re.findall(r"\d+", line))) for line in data]
for claim in claims:
    _, left, top, width, height = claim
    for patch in [(i, j) for i in range(left, left+width) for j in range(top, top+height)]:
        patches[patch] = patches.get(patch, 0) + 1

print(len([n for n in patches.values() if n > 1]))
