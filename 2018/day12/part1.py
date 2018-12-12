#!/usr/bin/env python3

generations = 20


def parse(s):
    return [i for i in range(len(s)) if s[i] is '#']


data = open('input.txt').read().splitlines()
initial_state = data[0].split(": ")[1]
plants = parse(initial_state)

rules, states = [], []
for line in data[2:]:
    s_rule, state = line.split(' => ')
    rule = [i-2 for i in parse(s_rule)]
    rules.append(rule)
    states.append(state)
assert len(rules) == len(states)

for step in range(generations):
    next_gen = []
    for n in range(min(plants)-2, max(plants)+3):
        plants_slice = [i for i in range(-2, +3) if i+n in plants]
        rule_index = rules.index(plants_slice) if plants_slice in rules else None
        if rule_index is not None:
            if states[rule_index] is '#':
                next_gen.append(n)
    plants = next_gen

print(sum(plants))
