#include <stdio.h>
#include <limits.h>

#define SIZE 2000  // Input file size

int main()
{
    int previous = INT_MAX;
    int measurement = 0;
    int increases = 0;
    while (fscanf(stdin, "%d", &measurement) == 1) {
        if (measurement > previous)
            increases++;
        previous = measurement;
    }
    printf("%d\n", increases);
}
