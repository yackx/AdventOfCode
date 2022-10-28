import sys


grid = None
n_rows = None
n_cols = None


class Grid:
    cells: [[int]]
    flashes = 0
    cycle = 0

    @staticmethod
    def from_arr(octo: [[int]]):
        g = Grid()
        g.cells = octo  # not strictly encapsulated
        return g

    @property
    def n_rows(self):
        return len(self.cells)

    @property
    def n_cols(self):
        return len(self.cells[0])

    def coords(self):
        for r in range(self.n_rows):
            for c in range(self.n_cols):
                yield r, c

    def increase(self):
        self.cells = [
            [self.cells[row][col] + 1 for col in range(self.n_cols)]
            for row in range(self.n_rows)
        ]

    def neighbors(self, row, col):
        for d_r in range(-1, 2):
            for d_c in range(-1, 2):
                self_cell = d_r == 0 and d_c == 0
                if not self_cell and 0 <= row+d_r < self.n_rows and 0 <= col+d_c < self.n_cols:
                    yield row + d_r, col + d_c

    def has_flash(self):
        return any(self.cells[x][y] > 9 for x, y in self.coords())

    def flashing_cells(self):
        for x, y in self.coords():
            if self.cells[x][y] > 9:
                yield x, y

    def reset_after_flash(self):
        for x, y in self.coords():
            if self.cells[x][y] < 0 or self.cells[x][y] > 9:
                self.cells[x][y] = 0

    def is_all_flashing(self):
        return all(self.cells[x][y] < 0 or self.cells[x][y] > 9 for x, y in self.coords())

    def evolve(self, cycles: int = 1, reset_flashes_after_cycle=True):
        self.cycle += 1
        for cycle in range(cycles):
            self.increase()
            while self.has_flash():
                for x, y in self.flashing_cells():
                    self.cells[x][y] = -self.cells[x][y]
                    self.flashes += 1
                    for nx, ny in self.neighbors(x, y):
                        if self.cells[nx][ny] > 0:
                            self.cells[nx][ny] += 1
            if reset_flashes_after_cycle:
                self.reset_after_flash()  # not for part 2

        return self

    def solve_part_1(self, cycles: int = 100):
        self.evolve(cycles)
        return self.flashes

    def solve_part_2(self):
        while self.evolve(reset_flashes_after_cycle=False):
            if self.is_all_flashing():
                return self.cycle
            self.reset_after_flash()

    def __str__(self) -> str:
        return str(self.cells)


if __name__ == "__main__":
    octopuses = [
        [int(c) for c in line.rstrip()]
        for line in sys.stdin.readlines() if line != "\n"
    ]

    print(Grid.from_arr(octopuses).solve_part_1(100))
    print(Grid.from_arr(octopuses).solve_part_2())
