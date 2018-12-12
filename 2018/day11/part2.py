#!/usr/bin/env python3

import numpy


def compute_power(x, y, serial):
    rack_id = x + 10
    fuel = rack_id * y
    fuel += serial
    fuel *= rack_id
    fuel = fuel // 100 % 10
    fuel -= 5
    return fuel


def compute_best(serial):
    # https://www.reddit.com/r/adventofcode/comments/a53r6i/2018_day_11_solutions/ebjosg2/
    grid = numpy.fromfunction(compute_power, (300, 300), dtype=int, serial=serial)
    best_x, best_y, best_size, best_power = 0, 0, 0, 0
    for width in range(3, 30):  # As the result gets smaller after a while, upper bound can be lowered (from 300)
        windows = sum(grid[x:x-width+1 or None, y:y-width+1 or None] for x in range(width) for y in range(width))
        maximum = int(windows.max())
        location = numpy.where(windows == maximum)
        print(width, maximum, location[0][0] + 1, location[1][0] + 1)
        if maximum > best_power:
            best_x, best_y, best_size, best_power = location[0][0], location[1][0], width, maximum

    return best_x, best_y, best_size, best_power


assert compute_best(18) == (90, 269, 16, 113)
assert compute_best(42) == (232, 251, 12, 119)

x, y, s, _ = compute_best(6548)
print(f"{x},{y},{s}")
