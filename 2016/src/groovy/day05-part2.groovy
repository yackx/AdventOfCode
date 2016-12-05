#!/usr/bin/env groovy

import java.security.MessageDigest

def md = MessageDigest.getInstance('MD5')

def md5 = { String text ->
	new BigInteger(1, md.digest(text.bytes))
			.toString(16)
			.padLeft(32, "0")
}

String input = 'wtnhxymk'

final def X = '_'
def password = X * 8 as char[]
def i = 0

while (password.contains(X)) {
    def hash = md5 "${input}${i}"
    if (hash.startsWith('00000')) {
        def pos = hash[5]
        if (pos.isInteger()) {
            pos = pos as int
            if (pos >= 0 && pos <= 7 && password[pos] == X as char) {
                password[pos] = hash[6]
                print "${password}\r"   // cheap cinematic
            }
        }
    }
    i++
}

println password
