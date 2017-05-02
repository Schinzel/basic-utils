package io.schinzel.basicutils.crypto.cryptohash;

/**
 *
 *
 * Created by schinzel on 2017-05-02.
 */
public interface ICryptoHash {
    String hash(String clearText);


    boolean areEqual(String clearText, String hashedString);
}
