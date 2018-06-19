package io.schinzel.basicutils.file;

import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;
import lombok.AllArgsConstructor;

/**
 * Purpose of this class is ...
 * <p>
 * Created by Schinzel on 2018-06-19
 */
@AllArgsConstructor
public class Bytes {
    private final byte[] mBytes;


    public String asString() {
        return UTF8.getString(mBytes);
    }


    public Str asStr() {
        return Str.create(this.asString());
    }


    public byte[] getByteArray() {
        return mBytes;
    }


    public byte[] copyByteArray() {
        return mBytes;
    }
}
