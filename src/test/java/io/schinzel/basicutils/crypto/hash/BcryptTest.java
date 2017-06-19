package io.schinzel.basicutils.crypto.hash;

import org.junit.Assert;
import org.junit.Test;

public class BcryptTest {

    @Test
    public void hash_OneStringHashedTwice_TheHashedStringsShouldNotBeEqual() {
        IHash hash = new Bcrypt(4);
        String clearText = "This is a string";
        Assert.assertNotEquals(hash.hash(clearText), hash.hash(clearText));
    }


    @Test
    public void constructor_NoArguments_IterationsShouldBe10() {
        Bcrypt bcrypt = new Bcrypt();
        Assert.assertEquals(10, bcrypt.mIterations);
    }
}