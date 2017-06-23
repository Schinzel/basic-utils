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
     * @param clearText A clear text string to hash
     * @return The argument string hashed
     */
    String hash(String clearText);


    /**
     * @param clearText  A clear text string to compare to the argument hashed text
     * @param hashedText A hashed test which to compare to the argument clear text string
     * @return True if the argument clear text string is the same string as was input when
     * the argument hashed string was created
     */
    boolean matches(String clearText, String hashedText);
}
