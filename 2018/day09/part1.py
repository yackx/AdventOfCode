#!/usr/bin/env python3

from collections import deque


def play(n_marbles, n_players):
    players = [0 for _ in range(n_players)]
    circle = deque([0])
    for marble in range(1, n_marbles + 1):
        if marble % 23 == 0:
            circle.rotate(7)
            m = circle.pop()
            players[marble % n_players] += marble + m
            circle.rotate(-1)
        else:
            circle.rotate(-1)
            circle.append(marble)

    return max(players)


assert play(25, 10) == 32
assert play(1618, 10) == 8317
assert play(7999, 13) == 146373
assert play(1104, 17) == 2764
assert play(6111, 21) == 54718
assert play(5807, 30) == 37305
print(play(71843, 468))


# Notes:
#
# The representation of the circle differs from the one in AoC sample,
# but as it is a circle, the starting marble does not matter.
# The current marble is always the last one.
#
# marble=1 circle=deque([0, 1])
# marble=2 circle=deque([1, 0, 2])
# marble=3 circle=deque([0, 2, 1, 3])
# marble=4 circle=deque([2, 1, 3, 0, 4])
# marble=5 circle=deque([1, 3, 0, 4, 2, 5])
# marble=6 circle=deque([3, 0, 4, 2, 5, 1, 6])
# marble=7 circle=deque([0, 4, 2, 5, 1, 6, 3, 7])
# marble=8 circle=deque([4, 2, 5, 1, 6, 3, 7, 0, 8])
# marble=9 circle=deque([2, 5, 1, 6, 3, 7, 0, 8, 4, 9])
# marble=10 circle=deque([5, 1, 6, 3, 7, 0, 8, 4, 9, 2, 10])
