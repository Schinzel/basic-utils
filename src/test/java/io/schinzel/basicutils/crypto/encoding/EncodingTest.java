package io.schinzel.basicutils.crypto.encoding;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.UTF8;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
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
    public void encode_Base64HardcodedValue_HardcodedOutput() {
        String actual = Encoding.BASE64.encode(UTF8.getBytes("åäö"));
        String expected = "w6XDpMO2";
        assertEquals(expected, actual);
    }


    @Test
    public void encode_HexHardcodedValue_HardcodedOutput() {
        String actual = Encoding.HEX.encode(UTF8.getBytes("åäö"));
        String expected = "c3a5c3a4c3b6";
        assertEquals(expected, actual);
    }


    @Test
    public void encode_Base16OddNumberOfChars_ThrowsException() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Encoding.HEX.decode("a");
        }).withMessageContaining("Invalid input length ");
    }


    @Test
    public void base62EncodeDecode_RandomString_DecodedStringSameAsInput() {
        String input = RandomUtil.getRandomString(100);
        byte[] inputAsBytes = UTF8.getBytes(input);
        String encodedString = Encoding.BASE62.encode(inputAsBytes);
        byte[] decodedBytes = Encoding.BASE62.decode(encodedString);
        String decodedString = UTF8.getString(decodedBytes);
        assertEquals(input, decodedString);
    }

}