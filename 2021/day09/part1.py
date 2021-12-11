import sys


def calculate_risk(heigths):
    def get_cell(row, col):
        large = 10  # larger than single digit
        try:
            if row < 0 or col < 0:
                return large
            return heigths[row][col]
        except IndexError:
            return large

    def risk_for(row, col):
        deltas = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        cell = get_cell(row, col)
        if all(get_cell(row-dy, col-dx) > cell for dy, dx in deltas):
            return cell + 1
        else:
            return 0

    row_range = range(0, len(heigths))
    col_range = range(0, len(heigths[0]))
    return sum(risk_for(row, col) for row in row_range for col in col_range)


if __name__ == "__main__":
    heights = [
        [int(c) for c in line.rstrip()]
        for line in sys.stdin.readlines() if line != "\n"
    ]
    print(calculate_risk(heights))
