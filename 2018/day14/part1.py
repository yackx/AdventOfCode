#!/usr/bin/env python3


def score(attempts):
    recipes = [3, 7]
    elf1, elf2 = 0, 1
    elf = lambda e: (e + 1 + recipes[e]) % len(recipes)
    while len(recipes) < 10 + attempts:
        new_recipe = recipes[elf1] + recipes[elf2]
        digits = [int(c) for c in str(new_recipe)]
        recipes.extend(digits)
        elf1, elf2 = elf(elf1), elf(elf2)
    return ''.join([str(x) for x in recipes[attempts:attempts+10]])


assert score(9) == '5158916779'
assert score(5) == '0124515891'
assert score(18) == '9251071085'
assert score(2018) == '5941429882'

target = int(open('input.txt', 'r').read().strip())
print(score(target))
