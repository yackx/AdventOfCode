#!/usr/bin/env python3


def compute_fuel(cell, serial):
    x, y = cell
    rack_id = x + 10
    fuel = rack_id * y
    fuel += serial
    fuel *= rack_id
    fuel = fuel // 100 % 10
    fuel -= 5
    return fuel


def compute_best(serial):
    grid = {(i, j): compute_fuel((i, j), serial) for i in range(1, 301) for j in range(1, 301)}
    max_square, max_fuel = (0, 0), 0
    for i in range(1, 300-2+1):
        for j in range(1, 300-2+1):
            fuel = sum(grid[(ii, jj)] for ii in range(i, i+3) for jj in range(j, j+3))
            if fuel > max_fuel:
                max_fuel = fuel
                max_square = (i, j)

    return max_square, max_fuel


assert compute_fuel((3, 5), 8) == 4
assert compute_fuel((122, 79), 57) == -5
assert compute_fuel((217, 196), 39) == 0
assert compute_fuel((101, 153), 71) == 4

assert compute_best(18) == ((33, 45), 29)
assert compute_best(42) == ((21, 61), 30)

(x, y), _ = compute_best(6548)
print(f"{x},{y}")
