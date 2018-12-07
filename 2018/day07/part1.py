#!/usr/bin/env python3

import re
from collections import defaultdict

instructions = open('input.txt').read().splitlines()
predecessors = defaultdict(lambda: set())
for instruction in instructions:
    pre, step = re.findall(r'Step ([A-Z]).*([A-Z])', instruction)[0]
    predecessors[step].add(pre)
    if pre not in predecessors.keys():
        predecessors[pre] = set()

done = []
while predecessors:
    no_predecessor = sorted(k for (k, v) in predecessors.items() if len(v) == 0)[0]
    done.append(no_predecessor)
    predecessors.pop(no_predecessor)
    predecessors = {k: v-set(no_predecessor) for (k, v) in predecessors.items()}

print(''.join(done))
