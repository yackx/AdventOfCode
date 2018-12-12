After each generation, for about 250 iterations, if you print the following:

```
print(f'#{step} is {sum(next_gen)} difference {sum(next_gen) - sum(plants)}')

```

You observe that the difference in sum betwenn each generation becomes constant:

```
#161 is 12822 difference -391
#162 is 13275 difference 453
#163 is 13352 difference 77
#164 is 13420 difference 68
#165 is 13591 difference 171
#166 is 13958 difference 367
#167 is 13731 difference -227
#168 is 13713 difference -18
#169 is 13788 difference 75
#170 is 13863 difference 75
#171 is 13938 difference 75
#172 is 14013 difference 75
#173 is 14088 difference 75
#174 is 14163 difference 75
...
#250 is 19863 difference 75
```

In this case, the plants population increases by `75` for each iteration.

Therefore, after fifty billion generations: 

```
print((50000000000-250)*75 + 19863)
3750000001113
```

Depending on the puzzle input.
