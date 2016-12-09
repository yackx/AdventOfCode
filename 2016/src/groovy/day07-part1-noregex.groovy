#!/usr/bin/env groovy

def input = new File('day07.txt') as String[]

def supportsTls = { ip ->
    boolean tls = false
    boolean inHypernet = false
    for (int i = 0; i < ip.length() - 3; i++) {
        if (ip[i] == '[') {
            inHypernet = true
            continue
        }
        if (ip[i] == ']') {
            inHypernet = false
            continue
        }
        if (ip[i] == ip[i+3] && ip[i+1] == ip[i+2] && ip[i+2] != ip[i+3]) {
            if (inHypernet) {
                tls = false
                break
            } else {
                tls = true
            }
        }
    }
    tls
}

println input.count { ip -> supportsTls(ip) }


assert supportsTls('abba[mnop]qrst')
assert !supportsTls('abcd[bddb]xyyx')
assert !supportsTls('aaaa[qwer]tyui')
assert supportsTls('ioxxoj[asdfgh]zxcvbn')
assert !supportsTls('')
