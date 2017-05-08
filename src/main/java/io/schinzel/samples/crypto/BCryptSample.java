package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.hash.BCryptHash;
import io.schinzel.basicutils.crypto.hash.IHash;

public class BCryptSample {
    public static void main(String[] args) {
        IHash hash = new BCryptHash();
        String clearText = "This is a string";
        String hashedString1 = hash.hash(clearText);
        String hashedString2 = hash.hash(clearText);
        System.out.println(hashedString1);
        System.out.println(hashedString2);
        System.out.println("Matches? " + hash.matches(clearText, hashedString1));
        System.out.println("Matches? " + hash.matches(clearText, hashedString2));
    }
}
