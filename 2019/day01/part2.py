from math import floor


def calculate_fuel(mass: int) -> int:
    fuel = floor(mass / 3) - 2
    if fuel <= 0:
        return 0
    else:
        return fuel + calculate_fuel(fuel)


with open("input.txt", "r") as f:
    lines = f.read().splitlines()
    print(sum(calculate_fuel(int(mass)) for mass in lines))
