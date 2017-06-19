package io.schinzel.basicutils.crypto.hash;

import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class NoHashTest {
    @Test
    public void hash_RandomString_SameString() {
        String clearText = RandomUtil.getRandomString(20);
        String hashed = new NoHash().hash(clearText);
        assertEquals(clearText, hashed);
    }


    @Test
    public void matches_RandomString_SameString() {
        String clearText = RandomUtil.getRandomString(20);
        String hashed = new NoHash().hash(clearText);
        assertTrue("The clear text should match the hashed string",
                new NoHash().matches(clearText, hashed));
    }
}