package io.schinzel.basicutils;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
}