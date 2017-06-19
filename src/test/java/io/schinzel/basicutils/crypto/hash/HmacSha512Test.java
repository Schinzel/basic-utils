package io.schinzel.basicutils.crypto.hash;

import com.google.common.collect.ImmutableList;
import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import org.junit.Assert;
import org.junit.Test;

public class HmacSha512Test {

    @Test
    public void hash_OneStringHashedTwice_TheHashedStringsShouldBeEqual() {
        ImmutableList<IHash> hashes = new ImmutableList.Builder<IHash>()
                .add(new HmacSha512("0123456789abcdef", Encoding.BASE64))
                .add(new HmacSha512("0123456789abcdef", Encoding.HEX))
                .build();
        for (IHash hash : hashes) {
            for (FunnyChars funnyChars : FunnyChars.values()) {
                String clearText = funnyChars.getString();
                Assert.assertEquals(hash.hash(clearText), hash.hash(clearText));
            }
        }
    }


    @Test
    public void constructor_NoEncodingSet_EncodingShouldBeHex() {
        HmacSha512 hmacSha512 = new HmacSha512("0123456789abcdef");
        Assert.assertEquals(Encoding.HEX, hmacSha512.mEncoding);

    }


}