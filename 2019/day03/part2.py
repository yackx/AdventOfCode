def build_wire(path: str):
    wire = []
    current = (0, 0)
    sections = path.split(",")

    for section in sections:
        direction, length = section[0], int(section[1:])
        if direction == "U":
            dx, dy = 0, -1
        elif direction == "D":
            dx, dy = 0, 1
        elif direction == "L":
            dx, dy = -1, 0
        elif direction == "R":
            dx, dy = 1, 0
        else:
            raise ValueError(f"Unknown direction: {direction}")

        for step in range(0, length):
            x, y = current
            current = (x + dx, y + dy)
            wire.append(current)

    return wire


def intersect_wires(w1, w2):
    return set(w1).intersection(set(w2))


def signal_delay(w1, w2, point):
    return w1.index(point) + w2.index(point) + 2


def shortest_signal_delay(w1, w2, points):
    return min(signal_delay(w1, w2, point) for point in points)


if __name__ == '__main__':
    with open("input.txt", "r") as f:
        wire_1 = build_wire(f.readline())
        wire_2 = build_wire(f.readline())

    crossings = intersect_wires(wire_1, wire_2)
    print(shortest_signal_delay(wire_1, wire_2, crossings))
