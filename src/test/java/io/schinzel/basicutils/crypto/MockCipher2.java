package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.crypto.cipher.ICipher;

public class MockCipher2 implements ICipher {
    @Override
    public String encrypt(String clearText) {
        return "two_" + clearText;
    }


    @Override
    public String decrypt(String encryptedText) {
        return encryptedText.substring(4);
    }
}
