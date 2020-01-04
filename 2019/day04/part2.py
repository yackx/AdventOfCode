import sys
import itertools


def check_password(password: str) -> bool:
    digits = [int(d) for d in password]
    groups = [list(g) for k, g in itertools.groupby(password)]

    if not any(len(g) == 2 for g in groups):
        return False

    for i in range(0, len(digits) - 1):
        if digits[i+1] < digits[i]:
            return False

    return True


pass_from, pass_to = map(int, sys.argv[1].split("-"))
count = sum(check_password(str(i)) for i in range(pass_from, pass_to))

print(count)
