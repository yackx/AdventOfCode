import sys


def solve(crabs):
    def compute_fuel(c1, c2):
        distance = abs(c1 - c2)
        return distance * (distance+1) // 2

    compute_all_fuel = lambda c: sum(compute_fuel(c, crab) for crab in crabs)
    return min(compute_all_fuel(p) for p in range(min(crabs), max(crabs)))


if __name__ == "__main__":
    crabs = [int(s) for s in next(sys.stdin).split(",")]
    print(solve(crabs))
