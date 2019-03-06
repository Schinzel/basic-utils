package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.UTF8;

/**
 * The purpose of this class is if you want text in clear text and not encrypted. The benefit of this
 * is class is that classes that use ICipher can be more concise and readable if there is option to
 * not to encrypt, this class can be use instead of null checks or similar.
 * <p>
 * Created by schinzel on 2017-05-09.
 */
public class NoCipher implements ICipher {

    public static NoCipher create() {
        return new NoCipher();
    }


    @Override
    public String encrypt(byte[] clearText) {
        return UTF8.getString(clearText);
    }


    @Override
    public byte[] decryptToByteArray(String encryptedText) {
        return UTF8.getBytes(encryptedText);
    }
}
