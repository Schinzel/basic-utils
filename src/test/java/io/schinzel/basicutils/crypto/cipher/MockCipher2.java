package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.crypto.cipher.ICipher;

public class MockCipher2 implements ICipher {
    @Override
    public String encrypt(String clearTextString) {
        return "two_" + clearTextString;
    }


    @Override
    public String decrypt(String encryptedString) {
        return encryptedString.substring(4);
    }
}
