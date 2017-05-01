package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.str.Str;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.crypto.Cipher;

import static org.junit.Assert.assertEquals;

public class AESTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_WrongKeySize_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Key length must be 16 or 32");
        new AES("WrongKeySize");
    }


    @Test
    public void encryptAndDecrypt_funnyCharsAvailableEncodings_shouldBeTheSame() {
        for (Encoding encoding : Encoding.values()) {
            AES aes = AES.builder()
                    .key("0123456789abcdef")
                    .encoding(encoding)
                    .build();
            for (FunnyChars funnyChars : FunnyChars.values()) {
                String encrypted = aes.encrypt(funnyChars.getString());
                String decrypted = aes.decrypt(encrypted);
                assertEquals(funnyChars.getString(), decrypted);
            }
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


    @Test
    public void crypt_InvalidKeyLength_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Problems encrypting or decrypting string. Invalid AES key length");
        AES.crypt(new byte[]{1, 2, 3}, Cipher.ENCRYPT_MODE, "key", "InitVector123456");
    }


    @Test
    public void crypt_InvalidInitVectorLength_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Problems encrypting or decrypting string. Wrong IV length: must be 16 bytes long");
        AES.crypt(new byte[]{1, 2, 3}, Cipher.ENCRYPT_MODE, "0123456789abcdef", "InitVector");
    }

}