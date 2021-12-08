package main

import (
	"fmt"
	"bufio"
	"os"
	"strings"
	"strconv"
)

const boardSize = 5

type DrawSequence []int
type Board []*int  // nil = unmarked

func NewBoard() Board {
	return make([]*int, 0)
}

// Draw sets the board index containing number to nil
func (b Board) Draw(number int) {
	for i, n := range b {
		if n != nil && *n == number {
			b[i] = nil
		}
	}
}

type BingoIndexFunc func(int, int) int

func row(i, j int) int {
	return i + j * boardSize
}

func column(i, j int) int {
	return i * boardSize + j
}

// isBingoForFunc check if bingo is achieved on row or column
func (b Board) isBingoForFunc(indexFunc BingoIndexFunc) bool {
	for i := 0; i < boardSize; i++ {
		bingo := true
		for j := 0; j < boardSize; j++ {
			if b[indexFunc(i, j)] != nil {
				bingo = false
				break
			}
		}
		if bingo {
			return true
		}
	}
	return false
}

func (b Board) isBingo() bool {
	return b.isBingoForFunc(row) || b.isBingoForFunc(column)
}

func (b Board) score(number int) int {
	sum := 0
	for _, n := range b {
		if n != nil {
			sum += *n
		}
	}
	return sum * number
}

func readDrawSequence(scanner *bufio.Scanner) DrawSequence {
	scanner.Scan()
	line := scanner.Text()
	strSequence := strings.Split(line, ",")
	sequence := make([]int, 0, len(strSequence))
	for _, s := range strSequence {
		i, _ :=  strconv.Atoi(s)
		sequence = append(sequence, i)
	}
	return sequence
}

func readBoards(scanner *bufio.Scanner) []Board {
	boards := make([]Board, 0)
	var board Board
	for scanner.Scan() {
		line := scanner.Text()
		if line == "" {
			if board != nil {
				boards = append(boards, board)
			}
			board = NewBoard()
		} else {
			line = strings.Trim(line, " ")
			line = strings.ReplaceAll(line, "  ", " ")
			splitted := strings.Split(line, " ")
			for _, s := range splitted {
				i, _ := strconv.Atoi(s)
				board = append(board, &i)
			}
		}
	}
	return boards
}

func Solve(drawSequence DrawSequence, boards []Board) int {
	for _, number := range drawSequence {
		for _, board := range boards {
			board.Draw(number)
			if board.isBingo() {
				return board.score(number)
			}
		}
	}
	panic("no bingo")
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	drawSequence := readDrawSequence(scanner)
	boards := readBoards(scanner)
	solution := Solve(drawSequence, boards)
	fmt.Println(solution)
}
