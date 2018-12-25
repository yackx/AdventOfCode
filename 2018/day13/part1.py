#!/usr/bin/env python3

import operator

data = open('input.txt').read().splitlines()

# Build a grid of rails and carts: {(0, 0): '/', (0, 1): '-', ...}
grid = {(j, i): data[i][j] for i in range(len(data)) for j in range(len(data[i]))}

# Filter out blanks
grid = {k: v for k, v in grid.items() if v is not ' '}

# Extract carts: {(0, 2): '>', (3, 9): 'v'}
carts = {k: v for k, v in grid.items() if v in ['<', '>', 'v', '^']}

# Define a left next turn for each cart: {(0, 2): ('>', '<'), (3, 9): ('v', '<')}
carts = {k: (v, 'left') for k, v in carts.items()}

# Turn carts into '-' rails on the original grid
rails = {k: '-' if v in ['>', '<'] else '|' if v in ['^', 'v'] else v for k, v in grid.items()}

cart_directions = {
    '<': (-1, 0), '>': (+1, 0), 'v': (0, +1), '^': (0, -1),
}
rail_directions = {
    ('/', '^'): '>', ('/', '<'): 'v', ('/', '>'): '^', ('/', 'v'): '<',
    ('\\', '^'): '<', ('\\', '>'): 'v', ('\\', '<'): '^', ('\\', 'v'): '>',
}
turns = {
    # (turn, current_direction), new_direction
    ('left', '^'): '<', ('left', 'v'): '>', ('left', '>'): '^', ('left', '<'): 'v',
    ('straight', '<'): '<', ('straight', '>'): '>', ('straight', '^'): '^', ('straight', 'v'): 'v',
    ('right', '^'): '>', ('right', 'v'): '<', ('right', '>'): 'v', ('right', '<'): '^',
}
turn_sequence = ['left', 'straight', 'right']
turn_symbols = ['/', '\\']

# carts: {(2, 0): ('>', 'left'), (9, 3): ('v', 'left')}, ...}
while True:
    new_carts = {}
    print(f'\nDealing with {carts}')
    for position, (direction, next_turn) in carts.items():
        new_position = tuple(map(operator.add, position, cart_directions[direction]))
        print(f'{position}, {(direction, next_turn)} to {new_position}')
        new_rail = rails[new_position]
        if new_rail is '+':
            # crossing
            direction = turns[(next_turn, direction)]
            print('    ', next_turn, turn_sequence.index(next_turn), (turn_sequence.index(next_turn)+1) % (len(turn_sequence)))
            next_turn = turn_sequence[(turn_sequence.index(next_turn)+1) % (len(turn_sequence))]
        if new_rail in turn_symbols:
            # turn
            direction = rail_directions[(new_rail, direction)]
        # print(f'  {new_position} rail is {new_rail} => {(direction, next_turn)}')
        if new_position in new_carts.keys():
            # collision
            # print(f'COLLISION {new_position}')
            exit(0)
        new_carts[new_position] = (direction, next_turn)

    carts = new_carts
