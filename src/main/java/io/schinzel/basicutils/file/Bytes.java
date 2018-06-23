package io.schinzel.basicutils.file;

import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;

import java.util.Arrays;

/**
 * Purpose of this class is hold a byte array and allow it to be cast to other data types.
 * <p>
 * Created by Schinzel on 2018-06-19
 */
public class Bytes {
    /** An empty bytes instance */
    public static final Bytes EMPTY = new Bytes(EmptyObjects.EMPTY_BYTE_ARRAY);
    private final byte[] mBytes;


    Bytes(byte[] bytes) {
        mBytes = (bytes == null)
                ? EmptyObjects.EMPTY_BYTE_ARRAY
                : bytes;
    }


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
    @SuppressWarnings("WeakerAccess")
    public byte[] getByteArray() {
        return mBytes;
    }


    /**
     * @return A copy of the byte array held by this instance
     */
    @SuppressWarnings("WeakerAccess")
    public byte[] copyByteArray() {
        return Arrays.copyOf(mBytes, mBytes.length);
    }
}
