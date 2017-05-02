package io.schinzel.basicutils.crypto.cryptohash;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by schinzel on 2017-05-02.
 */
public class BCryptCryptoHash implements ICryptoHash {
    @Override
    public String hash(String clearText) {
        return BCrypt.hashpw(clearText, BCrypt.gensalt());
    }


    @Override
    public boolean areEqual(String clearText, String hashedString) {
        return BCrypt.checkpw(clearText, hashedString);
    }
}
