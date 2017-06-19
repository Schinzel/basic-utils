package io.schinzel.basicutils.crypto.hashlibrary;

import io.schinzel.basicutils.crypto.hash.IHash;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

/**
 * Extending the holder class for vanity code coverage points
 */
public class HashLibrarySingletonTest extends HashLibrarySingleton.Holder {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void getBuilder_GetLibraryCalledBefore_ThrowsException() {
        HashLibrarySingleton hashLibrarySingleton = new HashLibrarySingleton();
        hashLibrarySingleton.getLibrary();
        exception.expect(RuntimeException.class);
        hashLibrarySingleton.getBuilder();
    }


    @Test
    public void getLibrary_TwoCiphersAddedViaGetBuilder_ShouldEncryptWithCorrectPrefix() {
        String inputString = "thestring";
        IHash mockCipher1 = Mockito.mock(IHash.class);
        Mockito.when(mockCipher1.hash(inputString)).thenReturn("mock1");
        IHash mockCipher2 = Mockito.mock(IHash.class);
        Mockito.when(mockCipher2.hash(inputString)).thenReturn("mock2");
        HashLibrarySingleton hashLibrarySingleton = new HashLibrarySingleton();
        hashLibrarySingleton.getBuilder()
                .addHash(123, mockCipher1)
                .addHash(234, mockCipher2);
        String encryptedString1 = hashLibrarySingleton.getLibrary()
                .hash(123, inputString);
        String encryptedString2 = hashLibrarySingleton.getLibrary()
                .hash(234, inputString);
        assertEquals("v123_mock1", encryptedString1);
        assertEquals("v234_mock2", encryptedString2);
    }


}