#!/usr/bin/env python3

import re

data = sorted(open('sample.txt').read().splitlines())
print(sorted(data))

guards_by_minutes = dict()  # k = guard id, v = total minutes
current_guard = None
start = None
finish = None
for line in data:
    guard = re.findall(r".*#(\d+).*", line)
    if guard:
        print(guard)
        current_guard = int(guard[0])
    falls = re.findall(r".*:(\d\d).*falls", line)
    if falls:
        start = int(falls[0])
    wakes = re.findall(r".*:(\d\d).*wakes", line)
    if wakes:
        finish = int(wakes[0])
        guards_by_minutes[current_guard] =\
            guards_by_minutes.get(current_guard, 0) + finish - start

print(guards_by_minutes)
sleeper_id = max(guards_by_minutes, key=guards_by_minutes.get)
print(sleeper_id)
