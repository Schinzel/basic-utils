package io.schinzel.basicutils.crypto.cryptohash;

import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.schinzel.basicutils.FunnyChars;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitQuickcheck.class)
public class BCryptHashTest {

    @Test
    public void hash_OneStringHashedTwice_TheHashedStringShouldNotBeEqual() {
        String clearText = "This is a string";
        IHash hash = new BCryptHash();
        Assert.assertNotEquals(hash.hash(clearText), hash.hash(clearText));
    }


    @Test
    public void hashAndAreEqual_FunnyChars_ShouldBeEqual() {
        IHash hash = new BCryptHash();
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String clearText = funnyChars.getString();
            String hashed = hash.hash(clearText);
            Assert.assertTrue("The clear text string is the same as the hashed",
                    hash.areEqual(clearText, hashed));
        }
    }


}