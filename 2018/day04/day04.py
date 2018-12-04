#!/usr/bin/env python3

import re
from collections import Counter


def minutes_per_guard():
    """Return a dictionary with each minute spent sleeping for each guard.

    Repeating
    E.g. {
        10: [5, 6, 7, ..., 53, 54, 24, 25, 26, 27, 28],
        99: [40, 41, 42, ..., 48, 49, 36, 37, ..., 48, 49, 50, 51, 52, 53, 54]}
    """
    data = sorted(open('input.txt').read().splitlines())

    mpg = dict()
    current_guard, start = 0, 0
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
            current_mins = mpg.get(current_guard, [])
            mpg[current_guard] = current_mins + new_mins

    return mpg


def part1(minutes_per_guard):
    sleeper_id = max(minutes_per_guard, key=lambda x: len(minutes_per_guard[x]))
    # eg Counter({24: 2, 5: 1, 6: 1, ...}) (k = minute, v = how many times)
    frequencies = Counter(minutes_per_guard[sleeper_id])
    # eg 24 (minute where guard was mostly asleep)
    minute = max(frequencies, key=frequencies.get)

    return sleeper_id * minute


def part2(minutes_per_guard):
    # eg: {10: (24, 2), 99: (45, 3)}
    most_commons = {k: Counter(v).most_common(1)[0] for (k, v) in minutes_per_guard.items()}
    # eg: 99 (because 45 appears 3 times)
    sleeper_id = max(most_commons, key=lambda k: most_commons[k][1])
    # eg: 45
    minute = most_commons[sleeper_id][0]

    return sleeper_id * minute


mpg = minutes_per_guard()
print(part1(mpg))
print(part2(mpg))
