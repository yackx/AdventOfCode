package main

func computeCode(row, col int) int {
	// 1-based index

	// triangular number sequence offset
	// T(n) = n(n+1)/2 + 1 - n <=> T(n) = n(n-1)/2 + 1
	t := func(n int) int {
		return n*(n-1)/2 + 1
	}

	position := t(row+col-1) + col - 1

	code := 20151125
	for i := 1; i < position; i++ {
		code = (code * 252533) % 33554393
	}

	return code
}

func main() {
	row, col := 2947, 3029
	code := computeCode(row, col)
	println(code)
}
