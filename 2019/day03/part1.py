def build_wire(path: str):
    wire = set()
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
            wire.add(current)

    return wire


def distance_from_center(point):
    return abs(point[0]) + abs(point[1])


def shortest_distance_to_center(points):
    return min(distance_from_center(point) for point in points)


if __name__ == '__main__':
    with open("input.txt", "r") as f:
        wire_1 = build_wire(f.readline())
        wire_2 = build_wire(f.readline())

    crossings = wire_1.intersection(wire_2)
    print(shortest_distance_to_center(crossings))
