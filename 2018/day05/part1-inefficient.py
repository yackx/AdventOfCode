#!/usr/bin/env python3

# Grossly inefficient as the string is scanned many times

def reduce(s):
    replaced = True
    while replaced:
        replaced = False
        for i in range(len(s)-1):
            if s[i].lower() == s[i+1].lower():
                if s[i] != s[i+1]:
                    s = s.replace(s[i] + s[i+1], '', 1)
                    replaced = True
                    break

    return s


data = open('input.txt').readline().rstrip()
print(len(reduce(data)))
