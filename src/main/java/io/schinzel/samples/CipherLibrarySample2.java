package io.schinzel.samples;

import io.schinzel.basicutils.crypto.CipherLibrary;
import io.schinzel.basicutils.crypto.cipher.AES;
import io.schinzel.basicutils.crypto.cipher.ICipher;

/**
 * Using an enum can be beneficial for readability.
 */
public class CipherLibrarySample2 {
    public static void main(String[] args) {
        String encryptedMobileNumber = Crypto.MOBILE_NUMBER.encrypt("5551234");
        System.out.println(encryptedMobileNumber);
        String encryptedEmail = Crypto.EMAIL_ADDRESS.encrypt("dude@example.com");
        System.out.println(encryptedEmail);
        String encryptedSSN = Crypto.SSN.encrypt("12345");
        System.out.println(encryptedSSN);
        System.out.println(Crypto.decrypt(encryptedSSN));
    }


    enum Crypto {
        MOBILE_NUMBER(1, new AES("0123456789abcdef")),
        EMAIL_ADDRESS(2, new AES("mjynVBNTMEC8gHiN")),
        SSN(3, new AES("Cza6sBMEnXAtCoH3"));

        private final int mCipherVersion;


        Crypto(Integer version, ICipher cipher) {
            Holder.CIPHER_LIBRARY.addCipher(version, cipher);
            this.mCipherVersion = version;

        }


        String encrypt(String clearTextString) {
            return Holder.CIPHER_LIBRARY.encrypt(mCipherVersion, clearTextString);
        }


        static String decrypt(String encryptedString) {
            return Holder.CIPHER_LIBRARY.decrypt(encryptedString);
        }
    }


    private static class Holder {
        static CipherLibrary CIPHER_LIBRARY = new CipherLibrary();
    }
}
