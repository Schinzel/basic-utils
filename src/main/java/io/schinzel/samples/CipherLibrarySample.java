package io.schinzel.samples;

import io.schinzel.basicutils.crypto.CipherLibrary;
import io.schinzel.basicutils.crypto.cipher.AES;

public class CipherLibrarySample {


    public static void main(String[] args) {
        String encryptedString, decryptedString;
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new AES("0123456789abcdef"))
                .addCipher(2, new AES("mjynVBNTMEC8gHiN"))
                .addCipher(3, new AES("Cza6sBMEnXAtCoH3"));
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
