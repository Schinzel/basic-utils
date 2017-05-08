package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.cipher.AES;
import io.schinzel.basicutils.crypto.cipher.CipherLibrarySingleton;
import io.schinzel.basicutils.crypto.cipher.ICipher;

/**
 * Using an enum can be beneficial for readability.
 */
public class CipherLibraryEnumSample {
    public static void main(String[] args) {
        String encryptedMobileNumber = Cipher.MOBILE_NUMBER.encrypt("5551234");
        System.out.println(encryptedMobileNumber);
        String encryptedEmail = Cipher.EMAIL_ADDRESS.encrypt("dude@example.com");
        System.out.println(encryptedEmail);
        String encryptedSSN = Cipher.SSN.encrypt("12345");
        System.out.println(encryptedSSN);
        System.out.println(Cipher.decrypt(encryptedSSN));
    }


    enum Cipher {
        MOBILE_NUMBER(1, new AES("0123456789abcdef")),
        EMAIL_ADDRESS(2, new AES("mjynVBNTMEC8gHiN")),
        SSN(3, new AES("Cza6sBMEnXAtCoH3"));

        private final int mCipherVersion;


        Cipher(Integer version, ICipher cipher) {
            CipherLibrarySingleton.getBuilder().cipher(version, cipher);
            this.mCipherVersion = version;

        }


        String encrypt(String clearTextString) {
            return CipherLibrarySingleton.getLibrary().encrypt(mCipherVersion, clearTextString);
        }


        static String decrypt(String encryptedString) {
            return CipherLibrarySingleton.getLibrary().decrypt(encryptedString);
        }
    }


}
