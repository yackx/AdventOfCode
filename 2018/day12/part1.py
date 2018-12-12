#!/usr/bin/env python3

generations = 20

data = open('sample.txt').read().splitlines()
first_line = data[0].split(": ")[1]
plants = [i for i in range(len(first_line)) if first_line[i] is '#']
print(plants)

rules, states = [], []
for line in data[2:]:
    s_rule, state = line.split(' => ')
    rule = [i-2 for i in range(len(s_rule)) if s_rule[i] is '#']
    rules.append(rule)
    states.append(state)

print(rules, states)
assert len(rules) == len(states)

for step in range(generations):
    next_gen = []
    for n in range(min(plants)-2, max(plants)+3):
        slice = []
        for i in range(-2, +3):
            # if 0 <= i + n < len(plants):
            if i+n in plants:
                slice.append(i)
        # foo = [plants[i+n]-i for i in range(-2, +2) if 0 <= i + n < len(plants)]
        print(n, slice)
        rule_index = rules.index(slice) if slice in rules else None
        if rule_index is not None:
            print(f'rule found at {rule_index}')
            if states[rule_index] is '#':
                print('will live')
                next_gen.append(n)
            # elif state[rule_index] is '.':
            #     pass
            # else:
            #     raise Exception(f"no rule found for plant #{n} around {slice}")
        else:
            print('no rule')
    plants = next_gen
    print(next_gen)

print(sum(plants))