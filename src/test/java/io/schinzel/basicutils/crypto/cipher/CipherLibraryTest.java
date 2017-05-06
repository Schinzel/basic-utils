package io.schinzel.basicutils.crypto.cipher;

import io.schinzel.basicutils.FunnyChars;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class CipherLibraryTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void encryptDecrypt_FunnyChars_OutputSameAsInput() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1()).build();
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String encryptedString = cipherLibrary.encrypt(1, funnyChars.getString());
            String actual = cipherLibrary.decrypt(encryptedString);
            assertEquals(funnyChars.getString(), actual);
        }
    }


    @Test
    public void encrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToEncrypt() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1())
                .cipher(2, new MockCipher2())
                .build();
        String encryptedString = cipherLibrary.encrypt(1, "my_first_string");
        assertEquals("v1_one_my_first_string", encryptedString);
        encryptedString = cipherLibrary.encrypt(2, "my_second_string");
        assertEquals("v2_two_my_second_string", encryptedString);
    }


    @Test
    public void decrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToDecrypt() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1())
                .cipher(2, new MockCipher2())
                .build();
        String decryptedString = cipherLibrary.decrypt("v1_one_my_first_string");
        assertEquals("my_first_string", decryptedString);
        decryptedString = cipherLibrary.decrypt("v2_two_my_second_string");
        assertEquals("my_second_string", decryptedString);
    }


    @Test
    public void addCipher_CipherVersionAlreadyExists_ThrowsException() {
        CipherLibrary.CipherLibraryBuilder cipherLibraryBuilder = CipherLibrary.builder()
                .cipher(1, new MockCipher1())
                .cipher(1, new MockCipher2());
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Multiple entries with same key");
        cipherLibraryBuilder.build();
    }


    @Test
    public void encrypt_NoCypherWithArgumentVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1())
                .build();
        exception.expect(RuntimeException.class);
        cipherLibrary.encrypt(123, "my_string");
    }


    @Test
    public void decrypt_NoPrefix_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1()).build();
        exception.expect(RuntimeException.class);
        cipherLibrary.decrypt("my_string");
    }


    @Test
    public void decrypt_NoSuchCipherVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.builder()
                .cipher(1, new MockCipher1()).build();
        exception.expect(RuntimeException.class);
        cipherLibrary.decrypt("v123_my_string");
    }


}