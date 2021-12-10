import sys

def calculate_risk(highlights):
    def get_cell(row, col):
        try:
            return highlights[row][col]
        except IndexError:
            return 10  # bigger than single digit

    def risk_for(row, col):
        # print(f"risk for {row} {col}")
        deltas = [(-1, 0), (0, 1), (1, 0), (0, -1)]
        cell = get_cell(row, col)
        if all(get_cell(row-dy, col-dx) > cell for dy, dx in deltas):
            return cell + 1
        else:
            return 0

    row_range = range(0, len(highlights))
    col_range = range(0, len(highlights[0]))
    print(row_range, col_range)
    return sum(risk_for(row, col) for row in row_range for col in col_range)
    # print(f"row-range {row_range}")
    return sum(risk_for(row, col) for row in row_range for col in col_range)

if __name__ == "__main__":
    highlights = [
        [int(c) for c in line.rstrip()]
        for line in sys.stdin.readlines() if line != "\n"
    ]
    print(calculate_risk(highlights))
