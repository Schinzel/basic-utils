package io.schinzel.basicutils.crypto.encoding;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.UTF8;
import org.apache.commons.codec.DecoderException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class EncodingTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


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
    public void encode_Base64HardcodedValue_HardcodedOutput() {
        String actual = Encoding.BASE64.encode(UTF8.getBytes("åäö"));
        String expected = "w6XDpMO2";
        assertEquals(expected, actual);
    }


    @Test
    public void encode_HexHardcodedValue_HardcodedOutput() {
        String actual = Encoding.BASE16.encode(UTF8.getBytes("åäö"));
        String expected = "c3a5c3a4c3b6";
        assertEquals(expected, actual);
    }


    @Test
    public void encode() {
        exception.expect(DecoderException.class);
        exception.expectMessage("Odd number of characters");
        Encoding.BASE16.decode("a");
    }

}