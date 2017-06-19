package io.schinzel.samples.crypto;

import io.schinzel.basicutils.crypto.hash.HmacSha512;
import io.schinzel.basicutils.crypto.hash.IHash;

public class HmacSha512Sample {

    public static void main(String[] args) {
        String clearText = "my string";
        IHash hash = new HmacSha512("0123456789abcdef");
        String hashedText1 = hash.hash(clearText);
        String hashedText2 = hash.hash(clearText);
        System.out.println("Hash1: " + hashedText1);
        System.out.println("Hash1 matches: " + hash.matches(clearText, hashedText1));
        System.out.println("Hash2: " + hashedText2);
        System.out.println("Hash2 matches: " + hash.matches(clearText, hashedText2));
    }
}
