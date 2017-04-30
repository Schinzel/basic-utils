package io.schinzel.basicutils.crypto.encoding;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.UTF8;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncodingTest {

    @Test
    public void encodeDecode_FunnyCharsAllEncodings_InputSameAsOutput() {
        for (IEncoding encoding : Encoding.values()) {
            for (FunnyChars funnyChars : FunnyChars.values()) {
                String funnyString = funnyChars.getString();
                byte[] funnyBytes = UTF8.getBytes(funnyString);
                String encoded = encoding.encode(funnyBytes);
                byte[] decoded = encoding.decode(encoded);
                String actual = UTF8.getString(decoded);
                assertEquals(funnyString, actual);
            }
        }
    }


    @Test
    public void encode_HardcodedValue_HardcodedOutput(){
        String actual = Encoding.BASE64.encode(UTF8.getBytes("åäö"));
        String expected = "w6XDpMO2";
        assertEquals(expected, actual);
    }
}