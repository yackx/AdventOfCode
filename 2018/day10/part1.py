#!/usr/bin/env python3

import re

skip = 10629
take = 1


def boundaries(data):
    min_x = min([x for x, y in data])
    max_x = max([x for x, y in data])
    min_y = min([y for x, y in data])
    max_y = max([y for x, y in data])
    return min_x, max_x, min_y, max_y


def display(data):
    data = [(x, y) for x, y, _, _ in data]
    min_x, max_x, min_y, max_y = boundaries(data)
    data = [(x-min_x, y-min_y) for x, y in data]
    for i in range(max_y - min_y+1):
        for j in range(max_x - min_x + 1):
            c = '#' if (j, i) in data else ' '
            print(c, end='')
        print()


def iter_steps(data, iterations):
    for i in range(iterations):
        data = [(x+vx, y+vy, vx, vy) for x, y, vx, vy in data]
        yield data


def check_points_grouped(threshold, data):
    """To identify the iteration where letters become close enough"""
    min_x, max_x, min_y, max_y = boundaries(data)
    scale_x = max_x - min_x
    scale_y = max_y - min_y
    grouped = scale_x < threshold and scale_y < threshold
    if grouped:
        print(f"points in close range: {min_x},{max_x} {min_y},{max_y}")
    return grouped


data = open('input.txt').read().splitlines()
data = [map(int, re.findall(r"-?\d+", line)) for line in data]

sequence = iter_steps(data, skip+take)
for i in range(skip+take):
    data = next(sequence)
    if i >= skip:
        display(data)
