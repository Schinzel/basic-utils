package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.cipher.Aes256Gcm;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import io.schinzel.basicutils.crypto.encoding.Encoding;

public class Aes256Sample {

    public static void main(String[] args) {
        sampleForDocumentation();
        sampleEncryptAndDecrypt();
        sampleSameClearTextBecomesDifferentEncryptedString();
    }


    private static void sampleForDocumentation() {
        System.out.println("*** Example 1 ***");
        Aes256Gcm aes = new Aes256Gcm("0123456789abcdef0123456789abcdef");
        String encrypted1 = aes.encrypt("This is a string");
        String encrypted2 = aes.encrypt("This is a string");
        System.out.println("Encrypted 1: " + encrypted1);
        System.out.println("Encrypted 2: " + encrypted2);
        System.out.println("Decrypted 1: " + aes.decrypt(encrypted1));
        System.out.println("Decrypted 2: " + aes.decrypt(encrypted2));
    }


    private static void sampleEncryptAndDecrypt() {
        System.out.println("*** Example 2 ***");
        ICipher aes = new Aes256Gcm("0123456789abcdef0123456789abcdef", Encoding.BASE62);
        String encrypted = aes.encrypt("This is a string");
        String decrypted = aes.decrypt(encrypted);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }


    private static void sampleSameClearTextBecomesDifferentEncryptedString() {
        System.out.println("*** Example 3 ***");
        ICipher aes = new Aes256Gcm("0123456789abcdef0123456789abcdef", Encoding.BASE64);
        //The same clear text string becomes a different encrypted string each time
        System.out.println(aes.encrypt("This is a string"));
        System.out.println(aes.encrypt("This is a string"));
        System.out.println(aes.encrypt("This is a string"));
    }


}
