package io.schinzel.basicutils.crypto.hash;


public class MockHash2 implements IHash {
    @Override
    public String hash(String clearText) {
        return "mock2_" + clearText;
    }


    @Override
    public boolean matches(String clearText, String hashedString) {
        return this.hash(clearText) == hashedString;
    }
}
