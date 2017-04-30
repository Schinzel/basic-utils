package io.schinzel.basicutils.crypto.cipher;

/**
 * The purpose of this interface is to encrypt and decrypt string. All implementations of this
 * interface must support that any string encrypted with the encrypt method can be decrypted with
 * the decrypt method.
 * <p>
 * Created by schinzel on 2017-04-29.
 */
public interface ICipher {
    /**
     * Encrypts the argument string. A string encrypted with this method can be decrypted with the
     * decrypt method.
     *
     * @param clearTextString A clear test string to encrypt.
     * @return The argument string encrypted.
     */
    String encrypt(String clearTextString);


    /**
     * Decrypts the argument string.
     *
     * @param encryptedString An encrypted string to decrypt.
     * @return The argument string decrypted.
     */
    String decrypt(String encryptedString);

}
