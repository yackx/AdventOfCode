#!/usr/bin/env python3

import re

patches = lambda c: set([(i, j) for i in range(c[1], c[1]+c[3]) for j in range(c[2], c[2]+c[4])])
data = open('input.txt').read().splitlines()
claims = [list(map(int, re.findall(r"\d+", line))) for line in data]
print([c[0] for c in claims if all(len(patches(k) & patches(c)) is 0 for k in claims if c[0] is not k[0])])
