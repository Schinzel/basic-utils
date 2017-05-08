package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.cipher.AES;
import io.schinzel.basicutils.crypto.encoding.Encoding;

public class AesSample {

    public static void main(String[] args) {
        AES aes;
        String encryptedString, decryptedString;
        //Example 1
        System.out.println("*** Example 1 ***");
        aes = new AES("0123456789abcdef");
        encryptedString = aes.encrypt("This is a string");
        decryptedString = aes.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
        //Example 2
        System.out.println("*** Example 2 ***");
        aes = AES.builder()
                .key("0123456789abcdef")
                .encoding(Encoding.BASE16)
                .build();
        encryptedString = aes.encrypt("This is a string");
        decryptedString = aes.decrypt(encryptedString);
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
        //Example 3
        System.out.println("*** Example 3 ***");
        aes = AES.builder()
                .key("0123456789abcdef")
                .encoding(Encoding.BASE64)
                .build();
        //The same clear text string becommes a different encrypted string each time
        System.out.println(aes.encrypt("This is a string"));
        System.out.println(aes.encrypt("This is a string"));
        System.out.println(aes.encrypt("This is a string"));

    }

}
