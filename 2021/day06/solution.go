package main

import (
	"bufio"
	"fmt"
	"math/big"
	"os"
	"strconv"
	"strings"
)

func readPuzzle(scanner *bufio.Scanner) string {
	scanner.Scan()
	return scanner.Text()
}

func newFishSlice(puzzle string) []big.Int {
	fishes := make([]big.Int, 9)
	seq := strings.Split(puzzle, ",")
	for _, c := range seq {
		n, _ := strconv.Atoi(c)
		fishes[n].Add(big.NewInt(1), &fishes[n])
	}
	return fishes
}

func Solve(fishes []big.Int, cycles int) big.Int {
	for cycle := 0; cycle < cycles; cycle++ {
		newSpawns := fishes[0]
		for i := 1; i <= 8; i++ {
			fishes[i-1] = fishes[i]
		}
		fishes[8] = newSpawns
		fishes[6].Add(&newSpawns, &fishes[6])
	}
	score := big.NewInt(0)
	for _, n := range fishes {
		score.Add(&n, score)
	}
	return *score
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	puzzle := readPuzzle(scanner)

	fishes1 := newFishSlice(puzzle)
	score1 := Solve(fishes1, 80)
	fmt.Println(score1.String())

	fishes2 := newFishSlice(puzzle)
	score2 := Solve(fishes2, 256)
	fmt.Println(score2.String())
}
