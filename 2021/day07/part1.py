import sys


def solve(crabs):
    compute_fuel = lambda c1, c2: abs(c1 - c2)
    compute_all_fuel = lambda c: sum(compute_fuel(c, crab) for crab in crabs)
    return min(compute_all_fuel(p) for p in range(min(crabs), max(crabs)))


if __name__ == "__main__":
    crabs = [int(s) for s in next(sys.stdin).split(",")]
    print(solve(crabs))
