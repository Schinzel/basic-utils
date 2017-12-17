package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.thrower.Thrower;

import java.security.SecureRandom;

/**
 * The purpose of this class is to generate salts, i.e. a random sets of bytes.
 * <p>
 * Created by Schinzel on 2017-05-12.
 */
public class SaltShaker {
    private Integer mSize;
    private SecureRandom mRandom = new SecureRandom();


    /**
     *
     * @param saltSize The number of bytes of the salt this class will generate.
     */
    public SaltShaker(Integer saltSize) {
        Thrower.throwIfVarNull(saltSize, "saltSize");
        mSize = saltSize;
    }


    /**
     * @return A random set of bytes.
     */
    public byte[] getSalt() {
        byte[] salt = new byte[mSize];
        mRandom.nextBytes(salt);
        return salt;
    }

}
