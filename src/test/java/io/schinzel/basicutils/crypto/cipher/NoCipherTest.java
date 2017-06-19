package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.RandomUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoCipherTest {

    @Test
    public void encrypt_RandomString_SameString() {
        String clearText = RandomUtil.getRandomString(20);
        String encrypted = new NoCipher().encrypt(clearText);
        assertEquals(clearText, encrypted);
    }


    @Test
    public void decrypt_RandomString_SameString() {
        String randomString = RandomUtil.getRandomString(20);
        String decrypted = new NoCipher().decrypt(randomString);
        assertEquals(randomString, decrypted);
    }
}