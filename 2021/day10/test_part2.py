import pytest

from part2 import check_line, solve


def test_empty_line():
    assert check_line("") == 0


@pytest.mark.parametrize(
    "line", [
        "<>", "[]", "{}", "()"
        "([])", "{()()()}", "<([{}])>",
        "[<>({}){}[([])<>]]", "(((((((((())))))))))",
    ])
def test_valid(line: str):
    assert check_line(line) == 0


# @pytest.mark.parametrize(
#     "line", ["(]", "{()()()>", "(((()))}", "<([]){()}[{}])"])
# def test_invalid_sequence(line: str):
#     assert check_line(line) > 0


@pytest.mark.parametrize(
    "line,score", [
        ("[({(<(())[]>[[{[]{<()<>>", 288957),
        ("[(()[<>])]({[<{<<[]>>(", 5566),
        ("(((({<>}<{<{<>}{[]{[]{}", 1480781 ),
        ("{<[[]]>}<{[{[{[]{()[[[]", 995444),
        ("<{([{{}}[<[[[<>{}]]]>[]]", 294),
    ])
def test_invalid_sequence_score(line: str, score: int):
    assert check_line(line) == score


def test_solve_sample():
    sample = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
(((({<>}<{<{<>}{[]{[]{}
{<[[]]>}<{[{[{[]{()[[[]
<{([{{}}[<[[[<>{}]]]>[]]"""

    assert solve(sample.split("\n")) == 288957
