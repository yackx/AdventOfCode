CC      := gcc
CFLAGS  := -std=c11 -Wall -Wpedantic -Wextra
# -DBIT_COUNT=5 -DDIGIT_COUNT=12

all: part1 part2

part2.out: part2.c
	$(CC) $(CFLAGS)

part1.out: part1.c
	$(CC) $(CFLAGS)

.PHONY: clean
clean:
	rm part1 part2
