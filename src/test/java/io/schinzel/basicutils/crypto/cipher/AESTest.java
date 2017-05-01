package io.schinzel.basicutils.crypto.cipher;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import io.schinzel.basicutils.str.Str;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import javax.crypto.Cipher;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
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
    public void constructorOneArg() {
        AES aes = new AES("0123456789abcdef");
        String expected = "abc";
        String actual = aes.decrypt(aes.encrypt(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void encryptAndDecrypt_FunnyCharsAvailableEncodings_ShouldBeTheSame() {
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


    @Property
    public void encryptAndDecrypt_PropertyBased_ShouldBeTheSame(String string) {
        AES aes = new AES("0123456789abcdef");
        String encString = aes.encrypt(string);
        String decString = aes.decrypt(encString);
        assertEquals(string, decString);
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