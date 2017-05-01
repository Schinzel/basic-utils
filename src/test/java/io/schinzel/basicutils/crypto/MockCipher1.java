package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.crypto.cipher.ICipher;

class MockCipher1 implements ICipher {
    @Override
    public String encrypt(String clearTextString) {
        return "one_" + clearTextString;
    }


    @Override
    public String decrypt(String encryptedString) {
        return encryptedString.substring(4);
    }
}
