package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.thrower.Thrower;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.val;

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
    /** Singleton instance */
    private static CipherLibrary SINGLETON_INSTANCE = new CipherLibrary();
    @Getter(AccessLevel.PRIVATE)
    private Map<Integer, ICipher> ciphers = new HashMap<>();


    /**
     * Private so that only create method is used.
     */
    private CipherLibrary() {
    }


    /**
     * @return A new instance.
     */
    public static CipherLibrary create() {
        return new CipherLibrary();
    }


    /**
     * @return A singleton instance
     */
    public static CipherLibrary getSingleton() {
        return SINGLETON_INSTANCE;
    }


    /**
     * @param version The version of the hash to add
     * @param cipher  The cipher to add
     * @return This for chaining
     */
    public CipherLibrary addCipher(Integer version, ICipher cipher) {
        if (this.getCiphers().containsKey(version)) {
            throw new RuntimeException("Cannot add cipher to CipherLibrary. This as there already exists a cipher with version " + version);
        }
        this.getCiphers().put(version, cipher);
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
     * @param version   The cipher version
     * @param clearText The string to encrypt
     * @return An encrypted string with version prefix.
     */
    public String encrypt(Integer version, String clearText) {
        Thrower.throwIfFalse(this.getCiphers().containsKey(version))
                .message("Cannot encrypt as there is no cipher with version " + version);
        ICipher cipher = this.getCiphers().get(version);
        return VersionPrefix.addVersionPrefix(version, cipher.encrypt(clearText));
    }


    /**
     * Decrypts the argument string.
     * <p>
     * The version of the cipher used to encrypt this string
     * Example argument: v123_thisisanencryptedstring
     *
     * @param encryptedStringWithVersion Encrypted string with version prefix.
     * @return The argument string decrypted.
     */
    public String decrypt(String encryptedStringWithVersion) {
        val versionPrefix = new VersionPrefix(encryptedStringWithVersion);
        Integer version = versionPrefix.getVersion();
        Thrower.throwIfFalse(this.getCiphers().containsKey(version))
                .message("Cannot decrypt as there is no cipher with version " + version);
        return this.getCiphers()
                .get(version)
                .decrypt(versionPrefix.getString());
    }
}
