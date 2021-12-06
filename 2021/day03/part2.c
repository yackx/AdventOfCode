#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#ifndef BIT_COUNT
#define BIT_COUNT 12
#endif

#ifndef DIGIT_COUNT
#define DIGIT_COUNT 1000
#endif

// Report entry: a line in the puzzle input
struct s_report_entry {
    char value[BIT_COUNT+1];
    int8_t discard;
};

/*
Calculate o2 or co2 rating

discard_fn should return a non-zero value if the report row
is to be discared delta based on delta (mostly 1 or mostly 0)
and the fact the report entry starts with 0 or not

inversion_factor is 1 for most common search, -1 otherwise
*/
int calculate_rating(
    struct s_report_entry *report[DIGIT_COUNT],
    int (*discard_fn)(int, int),
    int inversion_factor)
{
    int latest_kept_row;
    int discard_count = 0;
    for (int col = 0; col < BIT_COUNT; col++) {
        // Search most common bit
        int one_or_zero = 0;
        for (int row = 0; row < DIGIT_COUNT; row++) {
            if (!report[row]->discard) {
                int value = (int) report[row]->value[col];
                one_or_zero += value - '0' == 1 ? 1 : -1;
            }
        }

        // Opposite value for least common search
        one_or_zero *= inversion_factor;

        // Filter out report entries that do no match condition
        discard_count = 0;
        for (int row = 0; row < DIGIT_COUNT; row++) {
            int value = (int) report[row]->value[col];
            int starts_with_zero = value - '0' == 0;
            if (!report[row]->discard)
            report[row]->discard = report[row]->discard || discard_fn(one_or_zero, starts_with_zero);
            if (!report[row]->discard)
                latest_kept_row = row;
            discard_count += report[row]->discard;
        }

        if (discard_count == DIGIT_COUNT - 1)
            break;
    }

    // Convert str binary to int
    int decimal_value = 0;
    for (int i = 0; i < BIT_COUNT; i++) {
        int v = (int) (report[latest_kept_row]->value[i] - '0');
        if (v)
            decimal_value += 1 << (BIT_COUNT-i-1);
    }

    // Free memory
    for (int i = 0; i < DIGIT_COUNT; i++);

    return decimal_value;
}

int least_common_discard_fn(int one_or_zero, int starts_with_zero)
{
    return (one_or_zero > 0 && starts_with_zero) || (one_or_zero <= 0 && !starts_with_zero);
}

int most_common_discard_fn(int one_or_zero, int starts_with_zero)
{
    return (one_or_zero >= 0 && starts_with_zero) || (one_or_zero < 0 && !starts_with_zero);;
}

void reset(struct s_report_entry *report[DIGIT_COUNT]) {
    for (int row = 0; row < DIGIT_COUNT; row++)
        report[row]->discard = 0;
}

int main()
{
    // Build report from stdin
    struct s_report_entry *report[DIGIT_COUNT];
    int i = 0;
    char digits[BIT_COUNT+1];
    while (fscanf(stdin, "%s", digits) == 1) {
        struct s_report_entry *entry = malloc(sizeof(struct s_report_entry));
        strcpy(entry->value, digits);
        report[i] = entry;
        i++;
    }

    // Compute
    int o2_generator_rating = calculate_rating(report, most_common_discard_fn, 1);
    reset(report);
    int co2_scrubber_rating = calculate_rating(report, least_common_discard_fn, -1);
    
    printf("%d\n", o2_generator_rating * co2_scrubber_rating);
}
