package io.schinzel.basicutils.crypto.hash;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.crypto.encoding.Encoding;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * The purpose of this class is NOT TO PROTECT PASSWORDS. For this use BCrypt. The purpose of this
 * class is to protect PII and still have the data be searchable. To achieve this there is no salt
 * used and thus the same string hashed twice will look the same.
 * <p>
 * Created by schinzel on 2017-05-25.
 */
public class HmacSha512 implements IHash {
    final Encoding mEncoding;
    private final HashFunction mHashFunction;


    public HmacSha512(String key) {
        this(key, null);
    }


    public HmacSha512(String key, Encoding encoding) {
        Key secretKeySpec = new SecretKeySpec(UTF8.getBytes(key), "AES");
        mHashFunction = Hashing.hmacSha512(secretKeySpec);
        mEncoding = (encoding == null) ? Encoding.HEX : encoding;
    }


    /**
     * @param clearText A text to hash
     * @return The argument clear text string hashed.
     */
    @Override
    public String hash(String clearText) {
        //Get the clear text as bytes
        byte[] abClearText = UTF8.getBytes(clearText);
        //Get the hash
        byte[] hashed = mHashFunction.hashBytes(abClearText).asBytes();
        //Return the hashed string.
        return mEncoding.encode(hashed);
    }


    @Override
    public boolean matches(String clearText, String hashedText) {
        //If the argument clear text in its hashed form is equal to the argument hashed text.
        return this.hash(clearText).equals(hashedText);
    }
}
