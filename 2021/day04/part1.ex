defmodule Aoc.Year2021.Day04.Part1 do
  def mapper(number, n), do: n
  def mapper(number, number), do: nil
  def check_number(number, board) do
    Enum.map(board, fn n -> mapper(number, n) end)
  end

  def is_winning(board) do
    List.first(board) == 3
  end

  def draw([number | tail], boards) do
    boards = Enum.map(boards, fn b -> check_number(number, b) end)
    winning = Enum.filter(boards, fn b -> is_winning(b) end) |> List.first
    if winning do
      IO.puts "WIN " <> List.first(winning)
    end
    draw(tail, boards)
  end

  def draw([], boards) do
    raise "no more draws"
  end

  def main do
    input = IO.read(:stdio, :all) |> String.split("\n\n")
    [draw_input | boards_input] = input
    IO.puts draw_input
    draw_sequence = draw_input
    |> String.split(",")
    # |> Enum.map(fn s -> String.to_integer(s) end)
    for s <- draw_sequence, do: IO.puts s

    boards = Enum.map(
      boards_input,
      fn b -> b |> String.replace("\n", " ") |> String.split(" ") end
    )

    # for b <- boards, do: IO.puts b
    # for s <- List.first(boards) do
    #   IO.puts s
    # end

    boards = Enum.map(boards, fn b ->
      Enum.map(b, fn s -> String.to_integer(s) end)
    end)
    # IO.puts List.first(boards) # |> List.last
    draw(draw_sequence, boards)
  end

  def board_mapper(draw_sequence, board) do
    # board |> String.join() |> Enum.map(&String.to_integer/1)
  end


end

Aoc.Year2021.Day04.Part1.main()
