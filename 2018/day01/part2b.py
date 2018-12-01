#!/usr/bin/env python3

from itertools import accumulate, cycle

data = [int(line) for line in open('input.txt')]
seen = {0}
print(next(f for f in accumulate(cycle(data)) if f in seen or seen.add(f)))

# https://www.reddit.com/r/adventofcode/comments/a20646/2018_day_1_solutions/eauapmb/
