package io.schinzel.basicutils.crypto;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.crypto.hash.IHash;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HashLibraryTest {

    @Test
    public void addHash_AddSameVersionNumberTwice_ThrowsException() {
        HashLibrary hashLibrary = HashLibrary.create()
                .addHash(1, mock(IHash.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            hashLibrary.addHash(1, mock(IHash.class));
        }).withMessageContaining("Cannot add hash to HashLibrary");
    }


    @Test
    public void getSingleton_AddHash_TheAddedHashShouldBeAvailable() {
        IHash mock = mock(IHash.class);
        when(mock.hash("my_string")).thenReturn("hashed_string");
        HashLibrary.getSingleton().addHash(1, mock);
        String actual = HashLibrary.getSingleton().hash(1, "my_string");
        String expected = "v1_hashed_string";
        assertThat(actual).isEqualTo(expected);
        //Reset singleton
        HashLibrary.SINGLETON_INSTANCE = new HashLibrary();
    }


    @Test
    public void hash_AddTwoHash_CorrectHashIsUsed() {
        IHash mockHash1 = mock(IHash.class);
        when(mockHash1.hash(anyString())).then(i -> "mock1_" + i.getArgument(0));
        IHash mockHash2 = mock(IHash.class);
        when(mockHash2.hash(anyString())).then(i -> "mock2_" + i.getArgument(0));
        HashLibrary library = HashLibrary.create()
                .addHash(45, mockHash1)
                .addHash(77, mockHash2);
        assertEquals("v45_mock1_mystring", library.hash(45, "mystring"));
        assertEquals("v77_mock2_mystring", library.hash(77, "mystring"));
    }


    @Test
    public void hash_PropertyBased_HashedCorrectly() {
        IHash mockHash = mock(IHash.class);
        when(mockHash.hash(anyString())).then(i -> "mock_" + i.getArgument(0));
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String clearText = funnyChars.getString();
            int version = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
            HashLibrary library = HashLibrary.create()
                    .addHash(version, mockHash);
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
                .addHash(1, mock(IHash.class))
                .addHash(2, mock(IHash.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            library.hash(123, "");
        });
    }


    @Test
    public void matches_DoNotMatch_False() {
        IHash mockHash = mock(IHash.class);
        when(mockHash.matches(anyString(), anyString())).thenReturn(false);
        HashLibrary library = HashLibrary.create().addHash(11, mockHash);
        String clearText = "first_string";
        String hashedString = library.hash(11, "second_string");
        assertFalse("Should not match as are two different strings",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_Matches_True() {
        IHash mockHash = mock(IHash.class);
        when(mockHash.matches(anyString(), anyString())).thenReturn(true);
        HashLibrary library = HashLibrary.create().addHash(11, mockHash);
        String clearText = "the_string";
        String hashedString = library.hash(11, "the_string");
        assertTrue("Should match as are the same string",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_NonExistingVersionInVersionPrefix_Exception() {
        HashLibrary library = HashLibrary.create().addHash(11, mock(IHash.class));
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            library.matches("clear_text", "v23_this_prefix_does_not_exist");
        });
    }
}
