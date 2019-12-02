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
    codes = [int(x) for x in f.readline().split(",")]
    codes[1] = 12
    codes[2] = 2
    print(compute(codes))
