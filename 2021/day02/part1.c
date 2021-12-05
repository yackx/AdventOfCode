#include <stdio.h>
#include <string.h>

#define CMD_SIZE 20

int main()
{
    int depth = 0;
    int horizon = 0;
    char command[CMD_SIZE] = {0};
    int value;
    while (fscanf(stdin, "%s %d", command, &value) == 2) {
        if (strcmp(command, "forward") == 0) {
            horizon += value;
        }
        else if (strcmp(command, "down") == 0) {
            depth += value;
        }
        else if (strcmp(command, "up") == 0) {
            depth -= value;
        }
    }
    printf("%d\n", horizon * depth);
}
