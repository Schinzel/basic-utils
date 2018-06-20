package io.schinzel.basicutils.file;

import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;
import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Purpose of this class is hold a byte array and allow it to be cast to other data types.
 * <p>
 * Created by Schinzel on 2018-06-19
 */
@AllArgsConstructor
public class Bytes {
    private final byte[] mBytes;


    /**
     * @return The data held by this instance as a UTF-8 encoded string
     */
    public String asString() {
        return UTF8.getString(mBytes);
    }


    /**
     * @return The data held by this instance as a UTF-8 encoded Str
     */
    public Str asStr() {
        return Str.create(this.asString());
    }


    /**
     * @return The byte array held by this instance
     */
    public byte[] getByteArray() {
        return mBytes;
    }


    /**
     * @return A copy of the byte array held by this instance
     */
    public byte[] copyByteArray() {
        return Arrays.copyOf(mBytes, mBytes.length);
    }
}
