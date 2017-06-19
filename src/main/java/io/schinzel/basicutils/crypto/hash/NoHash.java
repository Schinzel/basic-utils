package io.schinzel.basicutils.crypto.hash;

/**
 * This class does not hash a string. This can be useful for getting more readable and concise
 * code if for example a class has an optional hash function this can be used instead of
 * if-then and null checks if the hash should be used or not.
 * <p>
 * Created by schinzel on 2017-05-09.
 */
public class NoHash implements IHash {
    @Override
    public String hash(String clearText) {
        return clearText;
    }


    @Override
    public boolean matches(String clearText, String hashedText) {
        return clearText.equals(hashedText);
    }
}
