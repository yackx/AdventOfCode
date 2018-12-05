from string import *


# https://www.reddit.com/r/adventofcode/comments/a3912m/2018_day_5_solutions/eb4jzni/
def collapse(s):
    p = ['.']
    for u in s:
        v = p[-1]
        if v != u and v.lower() == u.lower():
            p.pop()
        else:
            p.append(u)
    return len(p) - 1


s = open('input.txt').read().strip()
print(collapse(s))
print(min(collapse(c for c in s if c.lower() != x) for x in ascii_lowercase))
