package io.schinzel.basicutils.crypto.hash;

import com.google.common.collect.ImmutableList;
import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {


    @Test
    public void matches_FunnyChars_ClearTextAndHashedClearTextShouldMatch() {
        ImmutableList<IHash> hashes = new ImmutableList.Builder<IHash>()
                .add(new Bcrypt(4))
                .add(new HmacSha512("0123456789abcdef", Encoding.BASE64))
                .add(new HmacSha512("0123456789abcdef", Encoding.HEX))
                .build();
        for (IHash hash : hashes) {
            for (FunnyChars funnyChars : FunnyChars.values()) {
                String clearText = funnyChars.getString();
                String hashed = hash.hash(clearText);
                Assert.assertTrue("The clear text string is the same as the hashed",
                        hash.matches(clearText, hashed));
            }
        }
    }


}