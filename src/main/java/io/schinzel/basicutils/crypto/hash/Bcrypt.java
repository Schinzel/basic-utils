package io.schinzel.basicutils.crypto.hash;

import org.mindrot.jbcrypt.BCrypt;

/**
 * The purpose of this class is to protect passwords.
 * Very thin wrapper of jBCrypt
 * http://www.mindrot.org/projects/jBCrypt/
 * <p>
 * Created by schinzel on 2017-06-01.
 */
public class Bcrypt implements IHash {
    final int mIterations;


    public Bcrypt() {
        this(10);
    }


    /**
     * This constructor exists for testing purposes. This number of iterations should in production
     * code not be less than 10. As bcrypt protection is to be slow this can bog down projects that
     * test extensively and frequently. So for testing purposes one can set a low iterations count.
     * With 10 iterations it takes ≈80 ms for a hash with 4 iterations it takes ≈2 ms for a hash
     * (2017 MacBook Pro 2,6 GHz Intel Core i7).
     *
     * @param iterations Minimum is 4.
     */
    public Bcrypt(int iterations) {
        mIterations = iterations;
    }


    @Override
    public String hash(String clearText) {
        return BCrypt.hashpw(clearText, BCrypt.gensalt(mIterations));
    }


    @Override
    public boolean matches(String clearText, String hashedText) {
        return BCrypt.checkpw(clearText, hashedText);
    }
}
