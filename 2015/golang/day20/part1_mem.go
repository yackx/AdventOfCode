package main

func lowestHouseNumber(threshold int) int {
	// Build a slice containing the number of presents for each house.
	// This consumes a lot of memory but avoid slow divisions.
	presents := make([]int, threshold/10+1)
	for i := 1; i <= threshold/10; i++ {
		for j := i; j <= threshold/10; j += i {
			presents[j] += i * 10
		}
	}

	// Find the first house with enough presents.
	for n := 1; n < len(presents); n++ {
		if presents[n] >= threshold {
			return n
		}
	}

	panic("No house found")
}

func main() {
	threshold := 33100000
	result := lowestHouseNumber(threshold)
	println(result)
}
