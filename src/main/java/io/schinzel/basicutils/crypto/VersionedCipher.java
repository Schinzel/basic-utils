package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.collections.keyvalues.IValueKey;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import lombok.Builder;

/**
 * Created by schinzel on 2017-04-30.
 */
public class VersionedCipher implements IValueKey{
    private final ICipher mCipher;
    private final String mPrefix;


    @Builder
    private VersionedCipher(ICipher cipher, int version) {
        mCipher = cipher;
        Thrower.throwIfVarOutsideRange(version, "version", 1, 1000000);
        mPrefix = "v" + String.valueOf(version) + "_";
    }


    public String encrypt(String clearText) {
        return this.mPrefix + mCipher.encrypt(clearText);
    }


    public String decrypt(String encryptedString) {
        if (!encryptedString.startsWith(mPrefix)) {
            throw new RuntimeException("Decryption failed. Wrong version. Cipher version: "
                    + mPrefix + ". String: '" + encryptedString + "'");
        }
        return mCipher.encrypt(encryptedString.substring(encryptedString.length()));
    }


    @Override
    public String getKey() {
        return mPrefix;
    }
}
