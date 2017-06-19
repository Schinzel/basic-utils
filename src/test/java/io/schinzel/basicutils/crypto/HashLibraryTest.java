package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.crypto.hash.IHash;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.assertj.core.api.Assertions.*;


public class HashLibraryTest {

    @Test
    public void addHash_AddSameVersionNumberTwice_ThrowsException() {
        HashLibrary hashLibrary = HashLibrary.create()
                .addHash(1, Mockito.mock(IHash.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            hashLibrary.addHash(1, Mockito.mock(IHash.class));
        }).withMessageContaining("Cannot add hash to HashLibrary");
    }


    @Test
    public void getSingleton_AddHash_TheAddedHashShouldBeAvailable() {
        IHash mock = Mockito.mock(IHash.class);
        Mockito.when(mock.hash("my_string")).thenReturn("hashed_string");
        HashLibrary.getSingleton().addHash(1, mock);
        String actual = HashLibrary.getSingleton().hash(1, "my_string");
        String expected = "v1_hashed_string";
        assertThat(actual).isEqualTo(expected);
        //Reset singleton
        HashLibrary.SINGLETON_INSTANCE = new HashLibrary();
    }


    @Test
    public void hash_AddTwoHash_CorrectHashIsUsed() {
        HashLibrary library = HashLibrary.create()
                .addHash(45, new MockHash("mock1"))
                .addHash(77, new MockHash("mock2"));
        assertEquals("v45_mock1_mystring", library.hash(45, "mystring"));
        assertEquals("v77_mock2_mystring", library.hash(77, "mystring"));
    }


    @Test
    public void hash_PropertyBased_HashedCorrectly() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String clearText = funnyChars.getString();
            int version = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
            HashLibrary library = HashLibrary.create()
                    .addHash(version, new MockHash());
            String actual = library.hash(version, clearText);
            String expected = "v" + version + "_mock_" + clearText;
            assertEquals(expected, actual);
        }
    }


    @Test
    public void hash_NoHashesAdded_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            HashLibrary.create().hash(123, "");
        });
    }


    @Test
    public void hash_RequestHashVersionThatDoesNotExist_Exception() {
        HashLibrary library = HashLibrary.create()
                .addHash(1, new MockHash())
                .addHash(2, new MockHash());
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            library.hash(123, "");
        });
    }


    @Test
    public void matches_DoNotMatch_False() {
        HashLibrary library = HashLibrary.create().addHash(11, new MockHash());
        String clearText = "first_string";
        String hashedString = library.hash(11, "second_string");
        assertFalse("Should not match as are two different strings",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_Matches_True() {
        HashLibrary library = HashLibrary.create().addHash(11, new MockHash());
        String clearText = "the_string";
        String hashedString = library.hash(11, "the_string");
        assertTrue("Should match as are the same string",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_NonExistingVersionInVersionPrefix_Exception() {
        HashLibrary library = HashLibrary.create().addHash(11, Mockito.mock(IHash.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            library.matches("clear_text", "v23_this_prefix_does_not_exist");
        });
    }
}
