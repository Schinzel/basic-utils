package io.schinzel.basicutils.crypto;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class VersionPrefixTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void addPrefix_AddPrefixToString_StringWithPrefix() {
        String expected = "v44_mystring";
        String actual = VersionPrefix.create(44).addPrefix("mystring");
        assertEquals(expected, actual);
    }


    @Test
    public void removePrefix_RemoveExistingPrefix_StringWihtoutPrefix() {
        String expected = "mystring";
        VersionPrefix vp = VersionPrefix.create(44);
        String stringWithPrefix = vp.addPrefix("mystring");
        String actual = vp.removePrefix(stringWithPrefix);
        assertEquals(expected, actual);
    }


    @Test
    public void removePrefix_RemoveNonExistingPrefix_ThrowsExeption() {
        exception.expect(RuntimeException.class);
        VersionPrefix.create(44).removePrefix("StringWithoutPrefix");
    }

}