package io.schinzel.basicutils;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.*;

public class UTF8Test {


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
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(UTF8::new)
                .withMessageStartingWith("UTF8 should not be instantiated");
    }


    @Test
    public void getBytes_Null_Null() {
        //noinspection ConstantConditions
        assertNull(UTF8.getBytes(null));
    }


    @Test
    public void getBytes_EmptyString_EmptyArray() {
        assertArrayEquals(new byte[0], UTF8.getBytes(""));
    }


    @Test
    public void getString_Null_Null() {
        //noinspection ConstantConditions
        assertNull(UTF8.getString(null));

    }


    @Test
    public void getString_EmptyArray_EmptyString() {
        assertEquals("", UTF8.getString(new byte[0]));

    }

}