import sys


def solve(nav: [str]) -> int:
    return sum(check_line(line) for line in nav)


def check_line(line: str) -> int:
    def check(opening, closing):
        return c == closing and len(stack) > 0 and stack[-1] == opening

    scores = {
        ')': 3,
        ']': 57,
        '}': 1197,
        '>': 25137,
    }
    stack = []
    for c in line:
        if any(x for x in [check("(", ")"), check("[", "]"), check("{", "}"), check("<", ">")]):
            stack.pop()
        elif c in ["(", "[", "{", "<"]:
            stack.append(c)
        else:
            return scores[c]

    return 0


if __name__ == "__main__":
    navigation = [line.rstrip() for line in sys.stdin.readlines() if line != "\n"]
    print(solve(navigation))
