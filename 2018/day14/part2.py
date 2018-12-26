#!/usr/bin/env python3


def score(target):
    recipes = '37'
    elf1, elf2 = 0, 1

    # Factoring into a lambda expression is slower (no inlining)
    elf = lambda e: (e + 1 + int(recipes[e])) % len(recipes)

    # The slice is mandatory for the computation to complete
    # in a reasonable timeframe
    while target not in recipes[-len(target)-1:]:
        recipes += str(int(recipes[elf1]) + int(recipes[elf2]))
        elf1, elf2 = elf(elf1), elf(elf2)

    return recipes.index(target)


assert score('51589') == 9
assert score('01245') == 5
assert score('92510') == 18
assert score('59414') == 2018

target = open('input.txt', 'r').read().strip()
print(score(target))
