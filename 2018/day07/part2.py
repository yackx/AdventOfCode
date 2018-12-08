#!/usr/bin/env python3

import re
from collections import defaultdict

workers_count = 2
time_adjustment = 0

# Not working, not finished
exit(1)

def build_predecessors():
    instructions = open('sample.txt').read().splitlines()
    p = defaultdict(lambda: set())
    for instruction in instructions:
        pre, step = re.findall(r'Step ([A-Z]).*([A-Z])', instruction)[0]
        p[step].add(pre)
        if pre not in p.keys():
            p[pre] = set()
    return p


def iter_steps(predecessors):
    while predecessors:
        # print(predecessors)
        no_predecessor = sorted(k for (k, v) in predecessors.items() if len(v) == 0)[0]
        yield no_predecessor
        predecessors.pop(no_predecessor)
        predecessors = {k: v-set(no_predecessor) for (k, v) in predecessors.items()}


def time(step):
    return time_adjustment + ord(step) - ord('A') + 1


min_not_0 = lambda x: min([e for e in x if e > 0], default=0)
workers = [0 for _ in range(workers_count)]
p = build_predecessors()
t = 0
for step in iter_steps(p):
    print('step', step)
    free_worker = workers.index(min(workers))
    workers[free_worker] = time(step)
    while min_not_0(workers) > 0:
        print('workers', workers)
        smallest_duration = min_not_0(workers)
        print('small', smallest_duration)
        workers = [w-smallest_duration if w > 0 else 0 for w in workers]
        print('now', workers)
        t += smallest_duration

print(t)
