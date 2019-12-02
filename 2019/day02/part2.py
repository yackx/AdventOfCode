import itertools


def compute(codes):
    ip = 0
    while codes[ip] != 99:
        param_1 = codes[ip+1]
        param_2 = codes[ip+2]
        position = codes[ip+3]
        if codes[ip] == 1:    # add
            codes[position] = codes[param_1] + codes[param_2]
        elif codes[ip] == 2:  # multiply
            codes[position] = codes[param_1] * codes[param_2]
        else:
            raise ValueError(f"Invalid op code {codes[ip]}")
        ip = ip + 4

    return codes[0]


with open("input.txt", "r") as f:
    original_codes = [int(x) for x in f.readline().split(",")]
    for noun, verb in itertools.product(range(0, 100), range(0, 100)):
        candidate_codes = list(original_codes)
        candidate_codes[1] = noun
        candidate_codes[2] = verb
        if compute(candidate_codes) == 19690720:
            print(100 * noun + verb)
            break
