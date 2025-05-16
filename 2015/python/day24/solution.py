import sys

from functools import reduce
from itertools import combinations
from operator import mul


def best_qe(num_groups, packages):
    group_size = sum(packages) // num_groups
    for i in range(len(packages)):
        qes = [reduce(mul, c) for c in combinations(packages, i) 
               if sum(c) == group_size]
        if qes:
            return min(qes)


if __name__ == "__main__":
    packages = [int(x) for x in sys.stdin]
    qe_1 = best_qe(3, packages)
    print(qe_1)
    qe_2 = best_qe(4, packages)
    print(qe_2)
