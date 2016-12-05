#!/usr/bin/env groovy

import java.security.MessageDigest

def md5 = { String text ->
	def md = MessageDigest.getInstance('MD5')
	new BigInteger(1, md.digest(text.bytes))
			.toString(16)
			.padLeft(32, "0")
}

String input = 'wtnhxymk'
String password = ''
int i = 0

while (password.length() != 8) {
    def hash = md5 "${input}${i}"
    if (hash.startsWith('00000')) {
        password += hash[5]
        println password
    }
    i++
}

println password


/*
Note: the following md5 version yields the same result but is much slower
MessageDigest.getInstance('MD5')
		.digest(text.bytes)
		.collect { String.format("%02x", it) }
		.join('')
*/
