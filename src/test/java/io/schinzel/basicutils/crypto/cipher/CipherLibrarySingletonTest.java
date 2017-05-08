package io.schinzel.basicutils.crypto.cipher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CipherLibrarySingletonTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void getBuilder_GetLibraryCalledBefore_ThrowsException() {
        CipherLibrarySingleton.getLibrary();
        exception.expect(RuntimeException.class);
        CipherLibrarySingleton.getBuilder();
    }


    @Test
    public void getLibrary_TwoCiphersAddedViaGetBuilder_ShouldEncryptWithCorrectPrefix() {
        String inputString = "thestring";
        ICipher mockCipher1 = Mockito.mock(ICipher.class);
        Mockito.when(mockCipher1.encrypt(inputString)).thenReturn("mock1");
        ICipher mockCipher2 = Mockito.mock(ICipher.class);
        Mockito.when(mockCipher2.encrypt(inputString)).thenReturn("mock2");
        CipherLibrarySingleton.getBuilder()
                .cipher(123, mockCipher1)
                .cipher(234, mockCipher2);
        String encryptedString1 = CipherLibrarySingleton.getLibrary()
                .encrypt(123, inputString);
        String encryptedString2 = CipherLibrarySingleton.getLibrary()
                .encrypt(234, inputString);
        assertEquals("v123_mock1", encryptedString1);
        assertEquals("v234_mock2", encryptedString2);
    }


}