package io.schinzel.basicutils.crypto.cipher;

/**
 * Created by schinzel on 2017-04-29.
 */
public enum AESPadding {
    PADDING("AES/CBC/PKCS5PADDING"),
    NO_PADDING("AES/CTR/NoPadding");

    private final String mOption;


    AESPadding(String padding) {
        mOption = padding;
    }

    String getString(){
        return mOption;
    }
}
