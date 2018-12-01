#!/usr/bin/env python3

import itertools

data = [int(line) for line in open('input.txt')]
frequency = 0
seen = {0}
for change in itertools.cycle(data):
    frequency += change
    if frequency in seen:
        print(frequency)
        break
    seen.add(frequency)
