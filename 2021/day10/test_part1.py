import pytest

from part1 import check_line, solve


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


@pytest.mark.parametrize(
    "line", ["(]", "{()()()>", "(((()))}", "<([]){()}[{}])"])
def test_invalid_sequence(line: str):
    assert check_line(line) > 0


@pytest.mark.parametrize(
    "line,score", [
        ("(]", 57),
        ("{()()()>", 25137),
        ("(((()))}", 1197),
        ("<([]){()}[{}])", 3),
        ("{([(<{}[<>[]}>{[]{[(<()>", 1197),
        ("[[<[([]))<([[{}[[()]]]", 3),
        ("[{[{({}]{}}([{[{{{}}([]", 57),
        ("[<(<(<(<{}))><([]([]()", 3),
        ("<{([([[(<>()){}]>(<<{{", 25137),
    ])
def test_invalid_sequence_score(line: str, score: int):
    assert check_line(line) == score


def test_solve_sample():
    sample = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]"""

    assert solve(sample.split("\n")) == 26397
