package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.FunnyChars;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;


public class CipherLibraryTest {

    @Test
    public void getSingleton_CallingMethodTwice_ShouldBeSameInstance() {
        CipherLibrary singleton = CipherLibrary.getSingleton();
        assertThat(singleton).isEqualTo(CipherLibrary.getSingleton());
    }


    @Test
    public void encryptDecrypt_FunnyChars_OutputSameAsInput() {
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new MockCipher1());
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String encryptedString = cipherLibrary.encrypt(1, funnyChars.getString());
            String actual = cipherLibrary.decrypt(encryptedString);
            assertEquals(funnyChars.getString(), actual);
        }
    }


    @Test
    public void encrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToEncrypt() {
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new MockCipher1())
                .addCipher(2, new MockCipher2());
        String encryptedString = cipherLibrary.encrypt(1, "my_first_string");
        assertEquals("v1_one_my_first_string", encryptedString);
        encryptedString = cipherLibrary.encrypt(2, "my_second_string");
        assertEquals("v2_two_my_second_string", encryptedString);
    }


    @Test
    public void decrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToDecrypt() {
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new MockCipher1())
                .addCipher(2, new MockCipher2());
        String decryptedString = cipherLibrary.decrypt("v1_one_my_first_string");
        assertEquals("my_first_string", decryptedString);
        decryptedString = cipherLibrary.decrypt("v2_two_my_second_string");
        assertEquals("my_second_string", decryptedString);
    }


    @Test
    public void addCipher_CipherVersionAlreadyExists_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            CipherLibrary.create()
                    .addCipher(1, new MockCipher1())
                    .addCipher(1, new MockCipher1());
        });
    }


    @Test
    public void encrypt_NoCypherWithArgumentVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create().addCipher(1, new MockCipher1());
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.encrypt(123, "my_string");
        });
    }


    @Test
    public void decrypt_NoPrefix_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create().addCipher(1, new MockCipher1());
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.decrypt("my_string");
        });
    }


    @Test
    public void decrypt_NoSuchCipherVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, new MockCipher1());
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.decrypt("v123_my_string");
        });
    }


}