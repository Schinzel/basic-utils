package io.schinzel.basicutils.crypto.hashlibrary;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.crypto.hash.IHash;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class HashLibraryTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void hash_AddTwoHash_CorrectHashIsUsed() {
        HashLibrary library = HashLibrary.builder()
                .addHash(45, new MockHash("mock1"))
                .addHash(77, new MockHash("mock2"))
                .build();
        assertEquals("v45_mock1_mystring", library.hash(45, "mystring"));
        assertEquals("v77_mock2_mystring", library.hash(77, "mystring"));
    }


    @Test
    public void hash_PropertyBased_HashedCorrectly() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String clearText = funnyChars.getString();
            int version = RandomUtil.getRandomNumber(1, Integer.MAX_VALUE);
            HashLibrary library = HashLibrary.builder()
                    .addHash(version, new MockHash())
                    .build();
            String actual = library.hash(version, clearText);
            String expected = "v" + version + "_mock_" + clearText;
            assertEquals(expected, actual);
        }
    }


    @Test
    public void hash_NoHashesAdded_Exception() {
        HashLibrary library = HashLibrary.builder().build();
        exception.expect(RuntimeException.class);
        library.hash(123, "");
    }


    @Test
    public void hash_RequestHashVersionThatDoesNotExist_Exception() {
        HashLibrary library = HashLibrary.builder()
                .addHash(1, new MockHash())
                .addHash(2, new MockHash())
                .build();
        exception.expect(RuntimeException.class);
        library.hash(123, "");
    }


    @Test
    public void matches_DoNotMatch_False() {
        HashLibrary library = HashLibrary.builder()
                .addHash(11, new MockHash())
                .build();
        String clearText = "first_string";
        String hashedString = library.hash(11, "second_string");
        assertFalse("Should not match as are two different strings",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_Matches_True() {
        HashLibrary library = HashLibrary.builder()
                .addHash(11, new MockHash())
                .build();
        String clearText = "the_string";
        String hashedString = library.hash(11, "the_string");
        assertTrue("Should match as are the same string",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_NonExistingVersionInVersionPrefix_Exception() {
        HashLibrary library = HashLibrary.builder()
                .addHash(11, Mockito.mock(IHash.class))
                .build();
        exception.expect(RuntimeException.class);
        library.matches("clear_text", "v23_this_prefix_does_not_exist");
    }
}
