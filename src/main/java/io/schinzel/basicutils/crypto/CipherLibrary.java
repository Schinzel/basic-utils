package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.crypto.cipher.ICipher;
import io.schinzel.basicutils.substring.SubString;

import java.util.HashMap;
import java.util.Map;

/**
 * The purpose of this class is to hold a set of ciphers that are used to encrypt and decrypt
 * strings.
 * <p>
 * Strings encrypted with the instances of this class are prefixed with a version.
 * E.g. v12_dflkjsdfdj
 * <p>
 * This is useful for:
 * - Using different ciphers for different data sets.
 * - Upgrading ciphers as security requirements change. DES was considered secure a few years ago.
 * - Replacing keys that might have leaked or suspected of have been cracked.
 * - Incrementally replacing a cipher run-time.
 * <p>
 * Created by schinzel on 2017-04-30.
 */
public class CipherLibrary {
    private Map<Integer, ICipher> mCiphers = new HashMap<>();


    /**
     *
     * @return A new instance.
     */
    public static CipherLibrary create() {
        return new CipherLibrary();
    }


    /**
     * @param cipherVersion The version of the cipher.
     * @param cipher        The cipher to add.
     * @return This for chaining.
     */
    public CipherLibrary addCipher(Integer cipherVersion, ICipher cipher) {
        //If the argument version already existed in library
        if (mCiphers.get(cipherVersion) != null) {
            throw new RuntimeException("Cannot add cipher. A cipher with version " + cipherVersion + " already exists.");
        }
        mCiphers.put(cipherVersion, cipher);
        return this;
    }


    /**
     * Encrypts the argument string.
     * <p>
     * Finds cipher with argument version. The cipher must have been added with the addCipher
     * method. Argument string is encrypted with this cipher. The encrypted string is prefixed
     * with the version it was encrypted with.
     * Example return if argument version is 123: v123_thisisanencryptedstring
     *
     * @param cipherVersion   The cipher version
     * @param clearTextString The string to encrypt
     * @return An encrypted string with version prefix.
     */
    public String encrypt(Integer cipherVersion, String clearTextString) {
        ICipher cipher = mCiphers.get(cipherVersion);
        if (cipher == null) {
            throw new RuntimeException("No cipher with version " + cipherVersion);
        }
        return "v" + cipherVersion + "_" + cipher.encrypt(clearTextString);
    }


    /**
     * Decrypts the argument string.
     * <p>
     * The version of the cipher used to encrypt this string
     * Example argument: v123_thisisanencryptedstring
     *
     * @param encryptedString Encrypted string with version prefix.
     * @return The argument string decrypted.
     */
    public String decrypt(String encryptedString) {
        if (!CipherLibrary.hasVersionPrefix(encryptedString)) {
            throw new RuntimeException("Could not decrypt string '" + encryptedString + "' as was not in the required format v123_anystring.");
        }
        String versionAsString = SubString
                .create(encryptedString)
                .startDelimiter("v")
                .endDelimiter("_")
                .getString();
        Integer version = Integer.valueOf(versionAsString);
        ICipher cipher = mCiphers.get(version);
        if (cipher == null) {
            throw new RuntimeException("Could not decrypt string '" + encryptedString + "' as there was not cipher with version " + versionAsString + "'");
        }
        String stringToDecrypt = SubString.create(encryptedString).startDelimiter("_").getString();
        return cipher.decrypt(stringToDecrypt);
    }


    /**
     * @param s
     * @return True if the argument string has a version prefix
     */
    static boolean hasVersionPrefix(String s) {
        return s.matches("^v(\\d+)_.*$");
    }

}
