import sys


def check_password(password: str) -> bool:
    digits = [int(d) for d in password]
    found_adjacent_dups = False
    for i in range(0, len(digits) - 1):
        if digits[i+1] < digits[i]:
            return False
        if digits[i] == digits[i+1]:
            found_adjacent_dups = True

    return found_adjacent_dups


pass_from, pass_to = map(int, sys.argv[1].split("-"))
count = sum(check_password(str(i)) for i in range(pass_from, pass_to))

print(count)
