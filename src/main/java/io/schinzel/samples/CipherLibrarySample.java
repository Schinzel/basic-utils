package io.schinzel.samples;

import io.schinzel.basicutils.crypto.cipher.AES;
import io.schinzel.basicutils.crypto.cipher.CipherLibrary;

public class CipherLibrarySample {


    public static void main(String[] args) {
        String encryptedString, decryptedString;
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new AES("0123456789abcdef"))
                .cipher(2, new AES("mjynVBNTMEC8gHiN"))
                .cipher(3, new AES("Cza6sBMEnXAtCoH3"))
                .build();
        encryptedString = cipherLibrary.encrypt(2, "This is a string");
        decryptedString = cipherLibrary.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
        encryptedString = cipherLibrary.encrypt(3, "This is a string");
        decryptedString = cipherLibrary.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }


}
