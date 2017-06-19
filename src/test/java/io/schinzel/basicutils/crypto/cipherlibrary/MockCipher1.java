package io.schinzel.basicutils.crypto.cipherlibrary;

import io.schinzel.basicutils.crypto.cipher.ICipher;

class MockCipher1 implements ICipher {
    @Override
    public String encrypt(String clearText) {
        return "one_" + clearText;
    }


    @Override
    public String decrypt(String encryptedText) {
        return encryptedText.substring(4);
    }
}
