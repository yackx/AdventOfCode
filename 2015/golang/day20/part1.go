package main

func lowestHouseNumber(threshold int) int {
	house := 1
	for {
		presents := 0
		for elf := 1; elf <= house; elf++ {
			if house%elf == 0 {
				presents += elf * 10
			}
		}
		if presents >= threshold {
			return house
		}
		house++
	}
}

func main() {
	threshold := 33100000
	result := lowestHouseNumber(threshold)
	println(result)
}
