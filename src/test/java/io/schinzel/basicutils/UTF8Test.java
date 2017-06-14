package io.schinzel.basicutils;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class UTF8Test {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void getBytesAndGetString_ConvertCharsToBytesAndThenBackToString_OriginalString() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            byte[] bytes = UTF8.getBytes(funnyChars.getString());
            String string = UTF8.getString(bytes);
            assertEquals(funnyChars.getString(), string);
        }
    }


    @Test
    public void getBytes_stringToBytes_aSetOfHardCodedBytes() {
        byte[] expected = new byte[]{97, -61, -74, -59, -68, -48, -111, -40, -70};
        byte[] actual = UTF8.getBytes("aöżБغ");
        assertArrayEquals(expected, actual);
    }


    @Test
    public void constructor_Use_ThrowsException() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("should not be instantiated");
        new UTF8();
    }


    @Test
    public void getBytes_Null_Null() {
        assertNull(UTF8.getBytes(null));
    }


    @Test
    public void getBytes_EmptyString_EmptyArray() {
        assertArrayEquals(EmptyObjects.EMPTY_BYTE_ARRAY, UTF8.getBytes(""));
    }


    @Test
    public void getString_Null_Null() {
        assertNull(UTF8.getString(null));

    }


    @Test
    public void getString_EmptyArray_EmptyString() {
        assertEquals("", UTF8.getString(EmptyObjects.EMPTY_BYTE_ARRAY));

    }

}