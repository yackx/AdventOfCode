#include <stdio.h>
#include <limits.h>

#define SIZE 2000  // Input file size

int main()
{
    int i = 0;
    int measurments[SIZE];
    while (fscanf(stdin, "%d", &measurments[i++]) == 1);
    int increases = 0;
    int previous_sum = INT_MAX;
    for (i = 0; i < SIZE - 2; i++) {
        int sum = measurments[i] + measurments[i+1] + measurments[i+2];
        if (sum > previous_sum) {
            increases++;
        }
        previous_sum = sum;
    }
    printf("%d\n", increases);
}
