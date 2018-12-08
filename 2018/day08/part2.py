#!/usr/bin/env python3


def parse(data):
    n_children, n_meta = data[:2]
    data = data[2:]

    children_scores = [0 for _ in range(n_children)]
    for i in range(n_children):
        data, children_scores[i] = parse(data)

    meta = data[:n_meta]
    if n_children is 0:
        score = sum(meta)
    else:
        score = sum([children_scores[m-1] if m <= len(children_scores) else 0 for m in meta])

    return data[n_meta:], score


data = [int(x) for x in open('input.txt').read().strip().split()]
print(parse(data)[1])
