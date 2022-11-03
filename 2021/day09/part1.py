import sys


def calculate_risk(heights: [[int]]):
    def point_height(row, col):
        try:
            return heights[row][col]
        except IndexError:
            return 10  # larger than single digit

    def risk_for(row, col):
        deltas = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        height = point_height(row, col)
        if all(point_height(row-dy, col-dx) > height for dy, dx in deltas):
            return height + 1
        else:
            return 0

    row_range = range(len(heights))
    col_range = range(len(heights[0]))
    return sum(risk_for(row, col) for row in row_range for col in col_range)


if __name__ == "__main__":
    heights_map = [
        [int(c) for c in line.rstrip()]
        for line in sys.stdin.readlines() if line != "\n"
    ]
    print(calculate_risk(heights_map))
