CC      := gcc
CFLAGS  := -std=c18 -Wall -Wpedantic -Wextra

all: part1 part2

part2.out: part2.c
	$(CC) $(CFLAGS)

part1.out: part1.c
	$(CC) $(CFLAGS)

.PHONY: clean
clean:
	rm part1 part2
