package io.schinzel.basicutils.crypto.hash;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitQuickcheck.class)
public class HashLibraryTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void hash_AddTwoHash_CorrectHashIsUsed() {
        HashLibrary library = HashLibrary.builder()
                .hash(45, new MockHash1())
                .hash(77, new MockHash2())
                .build();
        assertEquals("v45_mock1_mystring", library.hash(45, "mystring"));
        assertEquals("v77_mock2_mystring", library.hash(77, "mystring"));
    }


    @Property
    public void hash_PropertyBased_HashedCorrectly(@InRange(min = "1") int version, String s) {
        HashLibrary library = HashLibrary.builder()
                .hash(version, new MockHash1())
                .build();
        String actual = library.hash(version, s);
        String expected = "v" + version + "_mock1_" + s;
        assertEquals(expected, actual);
    }


    @Test
    public void hash_NoHashesAdded_Exception() {
        HashLibrary library = HashLibrary.builder().build();
        exception.expect(RuntimeException.class);
        library.hash(123, "");
    }


    @Test
    public void hash_NonExistingVersionAsArg_Exception() {
        HashLibrary library = HashLibrary.builder()
                .hash(11, new MockHash1())
                .build();
        exception.expect(RuntimeException.class);
        library.matches("clearText", "v123_hashedstring");
    }


    @Test
    public void matches_DoNotMatch_False() {
        HashLibrary library = HashLibrary.builder()
                .hash(11, new MockHash1())
                .build();
        String clearText = "first_string";
        String hashedString = library.hash(11, "second_string");
        assertFalse("Should not match as are two different strings",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_Matches_True() {
        HashLibrary library = HashLibrary.builder()
                .hash(11, new MockHash1())
                .build();
        String clearText = "the_string";
        String hashedString = library.hash(11, "thre_string");
        assertFalse("Should match as are the same string",
                library.matches(clearText, hashedString));
    }


    @Test
    public void matches_NonExistingVersionPrefix_Exception() {
        HashLibrary library = HashLibrary.builder()
                .hash(11, new MockHash1())
                .build();
        exception.expect(RuntimeException.class);
        library.matches("clear_text", "v23_this_prefix_does_not_exist");
    }
}
