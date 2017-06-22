package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.crypto.cipher.ICipher;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class CipherLibraryTest {

    @Test
    public void getSingleton_CallingMethodTwice_ShouldBeSameInstance() {
        CipherLibrary singleton = CipherLibrary.getSingleton();
        assertThat(singleton).isEqualTo(CipherLibrary.getSingleton());
    }


    @Test
    public void encryptDecrypt_FunnyChars_OutputSameAsInput() {
        ICipher mockCipher = mock(ICipher.class);
        when(mockCipher.encrypt(anyString())).then(i -> "prefix_" + i.getArgument(0));
        when(mockCipher.decrypt(anyString())).then(i -> i.getArgument(0).toString().substring(7));
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, mockCipher);
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String encryptedString = cipherLibrary.encrypt(1, funnyChars.getString());
            String actual = cipherLibrary.decrypt(encryptedString);
            assertEquals(funnyChars.getString(), actual);
        }
    }


    @Test
    public void encrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToEncrypt() {
        ICipher mockCipher1 = mock(ICipher.class);
        when(mockCipher1.encrypt(anyString())).then(i -> "one_" + i.getArgument(0));
        ICipher mockCipher2 = mock(ICipher.class);
        when(mockCipher2.encrypt(anyString())).then(i -> "two_" + i.getArgument(0));
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, mockCipher1)
                .addCipher(2, mockCipher2);
        String encryptedString = cipherLibrary.encrypt(1, "my_first_string");
        assertEquals("v1_one_my_first_string", encryptedString);
        encryptedString = cipherLibrary.encrypt(2, "my_second_string");
        assertEquals("v2_two_my_second_string", encryptedString);
    }


    @Test
    public void decrypt_TwoDifferentCiphersAdded_CorrectCipherIsUsedToDecrypt() {
        ICipher mockCipher1 = mock(ICipher.class);
        when(mockCipher1.decrypt(anyString())).then(i -> i.getArgument(0).toString().substring(7));
        ICipher mockCipher2 = mock(ICipher.class);
        when(mockCipher2.decrypt(anyString())).then(i -> i.getArgument(0).toString().substring(5));
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, mockCipher1)
                .addCipher(2, mockCipher2);
        String decryptedString = cipherLibrary.decrypt("v1_prefix_my_first_string");
        assertEquals("my_first_string", decryptedString);
        decryptedString = cipherLibrary.decrypt("v2_bapp_my_second_string");
        assertEquals("my_second_string", decryptedString);
    }


    @Test
    public void addCipher_CipherVersionAlreadyExists_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            CipherLibrary.create()
                    .addCipher(1, mock(ICipher.class))
                    .addCipher(1, mock(ICipher.class));
        });
    }


    @Test
    public void encrypt_NoCypherWithArgumentVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create().addCipher(1, mock(ICipher.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.encrypt(123, "my_string");
        });
    }


    @Test
    public void decrypt_NoPrefix_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create().addCipher(1, mock(ICipher.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.decrypt("my_string");
        });
    }


    @Test
    public void decrypt_NoSuchCipherVersion_ThrowsException() {
        CipherLibrary cipherLibrary = CipherLibrary.create()
                .addCipher(1, mock(ICipher.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            cipherLibrary.decrypt("v123_my_string");
        });
    }


}