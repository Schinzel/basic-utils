package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.cipher.Aes256Gcm;
import io.schinzel.basicutils.crypto.CipherLibrary;

public class CipherLibrarySample {


    public static void main(String[] args) {
        String encryptedString, decryptedString;
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new Aes256Gcm("G2Ed3Zs6qDAft8Az7268BE9bWbAzXm2q"))
                .addCipher(2, new Aes256Gcm("uhgZxYLKn92w2B2ZFUtT2y98Z9K89MmX"))
                .addCipher(3, new Aes256Gcm("et4CgMZd99zPodK7QnLDGMV643GE94Q8"));
        encryptedString = cipherLibrary.encrypt(1, "This is a string");
        decryptedString = cipherLibrary.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
        encryptedString = cipherLibrary.encrypt(2, "This is a string");
        decryptedString = cipherLibrary.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }


}
