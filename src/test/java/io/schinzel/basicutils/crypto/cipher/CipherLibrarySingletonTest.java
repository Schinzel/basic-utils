package io.schinzel.basicutils.crypto.cipher;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Extending the holder class for vanity code coverage points
 */
public class CipherLibrarySingletonTest extends CipherLibrarySingleton.Holder {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void getBuilder_GetLibraryCalledBefore_ThrowsException() {
        CipherLibrarySingleton cipherLibrarySingleton = new CipherLibrarySingleton();
        cipherLibrarySingleton.getLibrary();
        exception.expect(RuntimeException.class);
        cipherLibrarySingleton.getBuilder();
    }


    @Test
    public void getLibrary_TwoCiphersAddedViaGetBuilder_ShouldEncryptWithCorrectPrefix() {
        String inputString = "thestring";
        ICipher mockCipher1 = Mockito.mock(ICipher.class);
        Mockito.when(mockCipher1.encrypt(inputString)).thenReturn("mock1");
        ICipher mockCipher2 = Mockito.mock(ICipher.class);
        Mockito.when(mockCipher2.encrypt(inputString)).thenReturn("mock2");
        CipherLibrarySingleton cipherLibrarySingleton = new CipherLibrarySingleton();
        cipherLibrarySingleton.getBuilder()
                .cipher(123, mockCipher1)
                .cipher(234, mockCipher2);
        String encryptedString1 = cipherLibrarySingleton.getLibrary()
                .encrypt(123, inputString);
        String encryptedString2 = cipherLibrarySingleton.getLibrary()
                .encrypt(234, inputString);
        assertEquals("v123_mock1", encryptedString1);
        assertEquals("v234_mock2", encryptedString2);
    }


}