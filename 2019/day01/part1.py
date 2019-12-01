from math import floor

with open("input.txt", "r") as f:
    lines = f.read().splitlines()
    print(sum(floor(int(mass) / 3) - 2 for mass in lines))
