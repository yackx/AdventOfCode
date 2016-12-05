#!/usr/bin/env groovy

import groovy.transform.CompileStatic

import java.security.MessageDigest

@CompileStatic
class Hacking {
    MessageDigest md = MessageDigest.getInstance('MD5')

    String md5(String text) {
    	new BigInteger(1, md.digest(text.bytes))
    			.toString(16)
    			.padLeft(32, '0')
    }

    char[] decipher(String input) {
        final def X = '_'
        char[] password = X * 8 as char[]
        int i = 0

        while (password.contains(X)) {
            String hash = md5 "${input}${i}"
            if (hash.startsWith('00000')) {
                String poz = hash[5]
                if (poz.isInteger()) {
                    int pos = poz as int
                    if (pos >= 0 && pos <= 7 && password[pos] == X as char) {
                        password[pos] = hash.charAt(6)
                        print "${password}\r"   // cheap cinematic
                    }
                }
            }
            i++
        }

        password
    }
}

def hacking = new Hacking()
println hacking.decipher('wtnhxymk')