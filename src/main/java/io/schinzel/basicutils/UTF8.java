package io.schinzel.basicutils;

import java.nio.charset.StandardCharsets;

/**
 * The purpose of this class is to convert strings into UTF-8 byte arrays and vice versa.
 * Created by schinzel on 2017-04-30.
 */
public class UTF8 {

    /**
     * Package private constructor as should not be instantiated.
     */
    UTF8() {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be instantiated. " +
                "Use the static methods.");
    }


    /**
     * @param string The string to encode.
     * @return The argument string as UTF-8 byte array.
     */
    public static byte[] getBytes(String string) {
        return (string == null) ? null : string.getBytes(StandardCharsets.UTF_8);
    }


    /**
     * @param bytes The bytes to decode.
     * @return The argument UTF-8 encoded bytes as a string.
     */
    public static String getString(byte[] bytes) {
        return (bytes == null) ? null : new String(bytes, StandardCharsets.UTF_8);
    }
}
