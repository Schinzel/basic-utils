package io.schinzel.basicutils.crypto.cipher;

/**
 * The purpose of this class is you want text in clear text and not encrypted. The benefit of this
 * is class is that classes that use ICipher can be more concise and readable if there is option to
 * not to encrypt, this class can be use instead of null checks or similar.
 * <p>
 * Created by schinzel on 2017-05-09.
 */
public class NoCipher implements ICipher {
    @Override
    public String encrypt(String clearText) {
        return clearText;
    }


    @Override
    public String decrypt(String encryptedText) {
        return encryptedText;
    }
}
