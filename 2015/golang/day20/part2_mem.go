package main

func lowestHouseNumber(threshold int) int {
	// Build a slice containing the number of presents for each house.
	// This consumes a lot of memory but avoid slow divisions.
	presents := make([]int, threshold/11+1)
	for elf := 1; elf <= threshold/11; elf++ {
		visited := 0
		for house := elf; house <= threshold/11; house += elf {
			presents[house] += elf * 11
			visited++
			if visited >= 50 {
				break
			}
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
