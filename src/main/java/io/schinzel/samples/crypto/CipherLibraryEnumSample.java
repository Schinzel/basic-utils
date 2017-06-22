package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.cipher.Aes256Gcm;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import io.schinzel.basicutils.crypto.CipherLibrary;

/**
 * Using an enum can be beneficial for readability.
 */
public class CipherLibraryEnumSample {
    public static void main(String[] args) {
        String encrypted = MyCipher.MOBILE_NUMBER.encrypt("12345");
        System.out.println(encrypted);
        System.out.println(MyCipher.decrypt(encrypted));
    }


    enum MyCipher {
        MOBILE_NUMBER(1, new Aes256Gcm("0123456789abcdef")),
        EMAIL_ADDRESS(2, new Aes256Gcm("mjynVBNTMEC8gHiN")),
        SSN(3, new Aes256Gcm("Cza6sBMEnXAtCoH3"));

        private final int mCipherVersion;


        MyCipher(Integer version, ICipher cipher) {
            CipherLibrary.getSingleton().addCipher(version, cipher);
            this.mCipherVersion = version;

        }


        String encrypt(String clearTextString) {
            return CipherLibrary.getSingleton().encrypt(mCipherVersion, clearTextString);
        }


        static String decrypt(String encryptedString) {
            return CipherLibrary.getSingleton().decrypt(encryptedString);
        }
    }


}
