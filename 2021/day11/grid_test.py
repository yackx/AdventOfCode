import pytest

from part1 import Grid


@pytest.fixture
def fixture_from_text():
    def create(text: str):
        arr = [
            [int(c) for c in line]
            for line in text.split("\n") if line
        ]

        return Grid.from_arr(arr)

    return create


@pytest.fixture
def fixture_mini_sample(fixture_from_text):
    text = """
11111
19991
19191
19991
11111
"""
    return fixture_from_text(text)


@pytest.fixture
def fixture_sample(fixture_from_text):
    text = """
5483143223
2745854711
5264556173
6141336146
6357385478
4167524645
2176841721
6882881134
4846848554
5283751526
"""
    return fixture_from_text(text)


def test_neighbors():
    grid = Grid.from_arr([[1, 1, 1, 1, 1], [1, 9, 9, 9, 1], [1, 9, 1, 9, 1], [1, 9, 9, 9, 1], [1, 1, 1, 1, 1]])
    n_0_0 = [x for x in grid.neighbors(0, 0)]
    assert_lists_same_elements([(1, 0), (1, 1), (0, 1)], n_0_0)


@pytest.mark.parametrize(["cycles", "expected_flashes"], [(1, 9), (2, 9)])
def test_evolve_mini_sample(fixture_mini_sample, cycles, expected_flashes):
    assert fixture_mini_sample.evolve(cycles).flashes == expected_flashes


@pytest.mark.parametrize(["cycles", "expected_flashes"], [(1, 0), (2, 35), (10, 204), (100, 1656)])
def test_evolve_sample(fixture_sample, cycles, expected_flashes):
    assert fixture_sample.evolve(cycles).flashes == expected_flashes


def assert_lists_same_elements(l1, l2):
    assert len(l1) == len(l2), "lists len mismatch"
    assert all(e in l2 for e in l1)
