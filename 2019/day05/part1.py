import itertools


def compute(codes, input_value):

    def fetch_in_mode(mode, parameter):
        if mode == 0:
            return codes[parameter]
        elif mode == 1:
            return parameter
        raise ValueError(f"Invalid mode {mode}")

    def get_parameter(param_number):
        return codes[ip + param_number]  # 1 = first param

    ip = 0
    while True:
        full_op_code = codes[ip]
        full_ops_code_str = str(full_op_code).zfill(5)
        op_code = int(full_ops_code_str[-2:])
        param_1_mode = int(full_ops_code_str[2])
        param_2_mode = int(full_ops_code_str[1])
        param_3_mode = int(full_ops_code_str[0])

        if op_code == 1:    # add
            param_1 = fetch_in_mode(param_1_mode, get_parameter(1))
            param_2 = fetch_in_mode(param_2_mode, get_parameter(2))
            assert param_3_mode == 0, param_3_mode
            param_3 = get_parameter(3)
            codes[param_3] = param_1 + param_2
            ip = ip + 4
        elif op_code == 2:  # multiply
            param_1 = fetch_in_mode(param_1_mode, get_parameter(1))
            param_2 = fetch_in_mode(param_2_mode, get_parameter(2))
            assert param_3_mode == 0, param_3_mode
            param_3 = get_parameter(3)
            codes[param_3] = param_1 * param_2
            ip = ip + 4
        elif op_code == 3:  # input
            param_1 = get_parameter(1)
            assert param_1_mode == 0, param_1_mode
            if input_value is None:
                raise ValueError("input_value already consumed")
            codes[param_1] = input_value
            input_value = None
            ip = ip + 2
        elif op_code == 4:  # output, position
            # assert param_1_mode == 0, param_1_mode
            param_1 = fetch_in_mode(param_1_mode, get_parameter(1))
            assert codes[param_1] == 0
            print(codes[param_1])
            ip = ip + 2
        elif op_code == 99:  # halt
            return codes[0]
        else:
            raise ValueError(f"Invalid op_code: [{op_code}] - read from file: {codes[ip]}")


with open("input.txt", "r") as f:
    original_codes = [int(x) for x in f.readline().split(",")]
    print(compute(original_codes, 1))
