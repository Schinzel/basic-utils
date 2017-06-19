package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.crypto.encoding.Encoding;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.crypto.Cipher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Aes256Test {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void constructor_KeyNonAsciiAndThusNo32Chars_ShouldStillWork() {
        Aes256 aes = new Aes256("ĄąŚśŹźŻżĄąŚśŹźŻż");
        String clearText = "This is a text";
        assertNotEquals(aes.encrypt(clearText), aes.encrypt(clearText));

    }


    @Test
    public void encrypt_OneStringEncryptedTwice_EncryptedStringsShouldDiffer() {
        Aes256 aes = new Aes256("0123456789abcdef0123456789abcdef");
        String clearText = "This is a text";
        assertNotEquals(aes.encrypt(clearText), aes.encrypt(clearText));
    }


    @Test
    public void constructor_WrongKeySize_ThrowException() {
        exception.expect(RuntimeException.class);
        new Aes256("ThisIsTheKey");
    }


    @Test
    public void constructor_ByteArrEmptyKey_ThrowException() {
        exception.expect(RuntimeException.class);
        new Aes256(new byte[]{}, Encoding.BASE64);
    }


    @Test
    public void constructor_ByteArrNullKey_ThrowException() {
        exception.expect(RuntimeException.class);
        new Aes256((byte[]) null, Encoding.BASE64);
    }


    @Test
    public void constructor_NullKey_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'key' cannot be empty");
        new Aes256(null);
    }


    @Test
    public void constructorOneArg() {
        Aes256 aes = new Aes256("0123456789abcdef0123456789abcdef");
        String expected = "abc";
        String actual = aes.decrypt(aes.encrypt(expected));
        assertEquals(expected, actual);
    }


    @Test
    public void encryptAndDecrypt_FunnyCharsAvailableEncodings_ShouldBeTheSame() {
        for (Encoding encoding : Encoding.values()) {
            Aes256 aes = new Aes256("0123456789abcdef0123456789abcdef", encoding);
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
        Aes256 aes = new Aes256("0123456789abcdef0123456789abcdef");
        String encrypted = aes.encrypt(expected);
        String decrypted = aes.decrypt(encrypted);
        assertEquals(expected, decrypted);
    }


    @Test
    public void crypt_InvalidKeyLength_ThrowException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Problems encrypting or decrypting string. Invalid AES key length");
        Aes256.crypt(new byte[]{1, 2, 3}, Cipher.ENCRYPT_MODE, UTF8.getBytes("key"), UTF8.getBytes("InitVector123456"));
    }


}