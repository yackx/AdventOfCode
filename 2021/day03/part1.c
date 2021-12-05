#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#ifndef BIT_COUNT
#define BIT_COUNT 12
#endif

#ifndef DIGIT_COUNT
#define DIGIT_COUNT 1000
#endif

int main()
{
    char report[DIGIT_COUNT][BIT_COUNT+1];
    int i = 0;
    while (fscanf(stdin, "%s", report[i++]) == 1);

    int gamma = 0;
    int epsilon = 0;
    for (int col = 0; col < BIT_COUNT; col++) {
        int one_or_zero = 0;
        for (int row = 0; row < DIGIT_COUNT; row++) {
            one_or_zero += (int)(report[row][col]) - '0' == 1 ? 1 : -1;
        }
        if (one_or_zero > 0) {
            gamma += 1 << (BIT_COUNT-col-1);
        }
        else {
            epsilon += 1 << (BIT_COUNT-col-1);
        }
    }
    printf("%d\n", gamma * epsilon);
}
