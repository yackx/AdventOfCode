#!/usr/bin/env python3

import re

patches = dict()
with open('input.txt') as f:
    for line in f:
        values = map(int, re.findall(r"\d+", line))
        _, left, top, width, height = values
        for i in range(left, left+width):
            for j in range(top, top+height):
                patch = (i, j)
                patches[patch] = patches.get(patch, 0) + 1

print(len([n for n in patches.values() if n > 1]))
