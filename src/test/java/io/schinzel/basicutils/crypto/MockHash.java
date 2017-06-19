package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.crypto.hash.IHash;

class MockHash implements IHash {
    private final String mMockPrefix;

    MockHash(){
        this("mock");
    }

    MockHash(String mockPrefix){
        mMockPrefix = mockPrefix;
    }

    @Override
    public String hash(String clearText) {
        return mMockPrefix + "_" + clearText;
    }


    @Override
    public boolean matches(String clearText, String hashedText) {
        return this.hash(clearText).equals(hashedText);
    }
}
