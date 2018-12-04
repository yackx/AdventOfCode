#!/usr/bin/env python3

import re
from collections import Counter

data = sorted(open('input.txt').read().splitlines())

minutes_per_guard = dict()              # k=guard_id, v=[all minutes asleep]
current_guard = None
start = None
finish = None
r_cycle = lambda s: rf".*:(\d\d).*{s}"  # falls or wakes

for line in data:
    guard = re.findall(r".*#(\d+).*", line)
    if guard:
        current_guard = int(guard[0])

    falls = re.findall(r_cycle('falls'), line)
    if falls:
        start = int(falls[0])

    wakes = re.findall(r_cycle('wakes'), line)
    if wakes:
        finish = int(wakes[0])
        new_mins = list(range(start, finish))
        current_mins = minutes_per_guard.get(current_guard, [])
        minutes_per_guard[current_guard] = current_mins + new_mins

# k = guard id, v = total number of minutes asleep
count_per_guard = {k: len(v) for (k, v) in minutes_per_guard.items()}
# sleeper id = max number of minutes asleep
sleeper_id = max(count_per_guard, key=count_per_guard.get)
# k = minute, v = how many times
frequencies = Counter(minutes_per_guard[sleeper_id])
# minute where guard was mostly asleep
minute = max(frequencies, key=frequencies.get)

print(sleeper_id * minute)
