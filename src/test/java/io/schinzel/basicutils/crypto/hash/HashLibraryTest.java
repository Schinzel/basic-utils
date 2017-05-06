package io.schinzel.basicutils.crypto.hash;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class HashLibraryTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void hash_AddTwoHash_CorrectHashIsUsed() {
        HashLibrary library = HashLibrary.create()
                .addHash(45, new MockHash1())
                .addHash(77, new MockHash2());
        assertEquals("v45_mock1_mystring", library.hash(45, "mystring"));
        assertEquals("v77_mock2_mystring", library.hash(77, "mystring"));
    }


    @Property
    public void hash_PropertyBased_HashedCorrectly(@InRange(min = "1") int version, String s) {
        HashLibrary library = HashLibrary.create().addHash(version, new MockHash1());
        String actual = library.hash(version, s);
        String expcected = "v" + version + "_mock1_" + s;
        assertEquals(expcected, actual);
    }


    @Test
    public void hash_NoHashesAdded_Exception() {
        HashLibrary library = HashLibrary.create();
        exception.expect(RuntimeException.class);
        library.hash(123, "");
    }


    @Test
    public void hash_NonExistingVersionAsArg_Exception() {
        HashLibrary library = HashLibrary.create()
                .addHash(45, new MockHash1());
        exception.expect(RuntimeException.class);
        library.matches("clearText", "v123_hashedstring");
    }


    @Test
    public void matches_DoNotMatch_False() {
    }


    @Test
    public void matches_Matches_True() {
    }


    @Test
    public void matches_NonExistingVersionPrefix_Exception() {
    }
}
