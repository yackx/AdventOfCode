package main

func lowestHouseNumber(threshold int) int {
	// Build a slice containing the number of presents for each house.
	// This consumes a lot of memory but avoid slow divisions.
	presents := make([]int, threshold/10+1)
	for elf := 1; elf <= threshold/10; elf++ {
		for house := elf; house <= threshold/10; house += elf {
			presents[house] += elf * 10
		}
	}

	// Find the first house with enough presents.
	for house := 1; house < len(presents); house++ {
		if presents[house] >= threshold {
			return house
		}
	}

	panic("No house found")
}

func main() {
	threshold := 33100000
	result := lowestHouseNumber(threshold)
	println(result)
}
