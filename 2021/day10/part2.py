import sys


def solve(nav: [str]) -> int:
    scores = [check_line(line) for line in nav]
    scores = [score for score in scores if score != 0]
    scores.sort()
    return scores[len(scores) // 2]


def check_line(line: str) -> int:
    def check(opening, closing):
        return c == closing and len(stack) > 0 and stack[-1] == opening

    points = {
        ')': 1,
        ']': 2,
        '}': 3,
        '>': 4,
    }
    bracket_pairs = {
        '(': ')',
        '[': ']',
        '{': '}',
        '<': '>'
    }
    stack = []
    is_corrupt = False
    for c in line:
        if any(x for x in [check(k, v) for k, v in bracket_pairs.items()]):
            stack.pop()
        elif c in ["(", "[", "{", "<"]:
            stack.append(c)
        else:
            is_corrupt = True
            break  # Ignore corrupt line

    if not is_corrupt:
        stack.reverse()
        score = 0
        for c in stack:
            score = score * 5 + points[bracket_pairs[c]]
        return score
    else:
        return 0


if __name__ == "__main__":
    navigation = [line.rstrip() for line in sys.stdin.readlines() if line != "\n"]
    print(solve(navigation))
