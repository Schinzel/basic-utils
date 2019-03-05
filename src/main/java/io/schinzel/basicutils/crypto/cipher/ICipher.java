package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.UTF8;

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
     * @param clearText A clear text string to encrypt.
     * @return The argument string encrypted.
     */
    default String encrypt(String clearText) {
        //Convert the clear text string to as utf8 bytes
        byte[] clearTextAsBytes = UTF8.getBytes(clearText);
        return this.encrypt(clearTextAsBytes);
    }


    /**
     * Encrypts the argument string. A string encrypted with this method can be decrypted with the
     * decrypt method.
     *
     * @param clearText Clear text to encrypt.
     * @return The argument string encrypted.
     */
    String encrypt(byte[] clearText);


    /**
     * Decrypts the argument string.
     *
     * @param encryptedText An encrypted string to decrypt.
     * @return The argument string decrypted.
     */
    default String decrypt(String encryptedText) {
        //Decrypt
        byte[] decryptedText = this.decryptToByteArray(encryptedText);
        //Convert the encrypted byte array to a string and return
        return UTF8.getString(decryptedText);
    }


    /**
     * Decrypts the argument string.
     *
     * @param encryptedText An encrypted string to decrypt.
     * @return The argument string decrypted.
     */
    byte[] decryptToByteArray(String encryptedText);
}
