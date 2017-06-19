package io.schinzel.basicutils.crypto.hash;

/**
 * The purpose of this interface is to prevent third parties or the public from reading private
 * data.
 * <p>
 * Probable implementation of this interface are cryptographic hash functions, password hashing
 * functions and password-based key derivation function.
 * <p>
 * Good article to read when choosing to implement hashing for keeping data secret.
 * https://crackstation.net/hashing-security.htm
 * <p>
 * Created by schinzel on 2017-05-02.
 */
public interface IHash {

    /**
     * @param clearText
     * @return The argument string hashed.
     */
    String hash(String clearText);


    /**
     * @param clearText
     * @param hashedText
     * @return True if the argument clear text string is the same string as was input when
     * the argument hashed string was created.
     */
    boolean matches(String clearText, String hashedText);
}
