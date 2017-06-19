package io.schinzel.basicutils.crypto.cipherlibrary;

import com.google.common.collect.ImmutableMap;
import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.crypto.VersionPrefix;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import lombok.*;

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
@Builder
public class CipherLibrary {
    @Singular("addCipher") @Getter(AccessLevel.PRIVATE)
    private ImmutableMap<Integer, ICipher> ciphers;


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
