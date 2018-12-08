#!/usr/bin/env python3

import re

lines = open('input.txt').read().splitlines()
instructions = [re.findall(r'Step ([A-Z]).*([A-Z])', i)[0] for i in lines]
print(instructions)


done = []
while instructions:
    if len(instructions) == 1:  # TODO Improve this unfortunate corner case
        a, b = instructions[0]
        done.append(a)
        done.append(b)
    steps_with_pred = [v for k, v in instructions]
    print('IN ', instructions)
    step = sorted([k for k, v in instructions if k not in steps_with_pred])[0]
    done.append(step)
    print('     ',steps_with_pred, step)
    instructions = [(k, v) for k, v in instructions if k is not done[-1]]
    print('OUT', instructions, done, step)

print(''.join(done))
