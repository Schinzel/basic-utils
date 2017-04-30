package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AESTest {


    @Test
    public void encryptAndDecrypt_funnyChars_shouldBeTheSame() {
        AES aes = AES.builder().key("0123456789abcdef").build();
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String encrypted = aes.encrypt(funnyChars.getString());
            String decrypted = aes.decrypt(encrypted);
            assertEquals(funnyChars.getString(), decrypted);
        }
    }


    @Test
    public void encryptAndDecrypt_longText_shouldBeTheSame() {
        RandomUtil random = RandomUtil.create(123);
        Str str = Str.create();
        for (int i = 0; i < 1000; i++) {
            str.a(random.getString(100));
        }
        String expected = str.getString();
        AES aes = AES.builder().key("0123456789abcdef").build();
        String encrypted = aes.encrypt(expected);
        String decrypted = aes.decrypt(encrypted);
        assertEquals(expected, decrypted);
    }

}