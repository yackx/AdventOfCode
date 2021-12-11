from part1 import calculate_risk


def test_single_point():
    heightmap = [[5]]
    assert calculate_risk(heightmap) == 6


def test_official_sample():
    input = '''2199943210
3987894921
9856789892
8767896789
9899965678'''
    heightmap = input_to_heightmap(input)
    # print(heightmap)
    assert calculate_risk(heightmap) == 15


def input_to_heightmap(input):
    print([line for line in input])
    return [
        [int(c) for c in line.strip()]
        for line in input.split("\n")
    ]
