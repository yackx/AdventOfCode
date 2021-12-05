TARGET  := $1.out
CC      := gcc
CFLAGS  := -std=c11 -Wall -Wpedantic -Wextra -O2

all: $(TARGET)

$(TARGET): ./$1.c
	$(CC) $(CFLAGS) -o $@ $^

clean:
	rm $(TARGET)
