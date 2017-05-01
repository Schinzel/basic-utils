package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.crypto.cipher.ICipher;
import io.schinzel.basicutils.substring.SubString;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to hold a set of ciphers.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
public class CipherLibrary {
    Map<Integer, ICipher> mCiphers = new HashMap<>();


    public CipherLibrary addCipher(Integer version, ICipher cipher) {
        if (mCiphers.get(version) != null) {
            throw new RuntimeException("Cannot add cipher. A cipher with version " + version + " already exists.");
        }
        mCiphers.put(version, cipher);
        return this;
    }


    public String encrypt(Integer version, String clearTextString) {
        ICipher cipher = mCiphers.get(version);
        if (cipher == null) {
            throw new RuntimeException("No cipher with version " + version);
        }
        return "v" + version + "_" + cipher.encrypt(clearTextString);
    }


    public String findCipherAndDecrypt(String encryptedString) {
        if (!CipherLibrary.isVersionedCipher(encryptedString)) {
            throw new RuntimeException("Could not decrypt string '" + encryptedString + "' as was not in the required format v123_anystring.");
        }
        String versionAsString = SubString.builder()
                .string(encryptedString)
                .startDelimiter("v")
                .endDelimiter("_")
                .build()
                .getString();
        Integer version = Integer.valueOf(versionAsString);
        ICipher cipher = mCiphers.get(version);
        if (cipher == null) {
            throw new RuntimeException("Could not decrypt string '" + encryptedString + "' as there was not cipher with version " + versionAsString + "'");
        }
        String stringToDecrypt = SubString.builder().string(encryptedString).startDelimiter("_").build().getString();
        return cipher.decrypt(stringToDecrypt);
    }


    static boolean isVersionedCipher(String s) {
        return s.matches("^v(\\d+)_.*$");
    }

}
