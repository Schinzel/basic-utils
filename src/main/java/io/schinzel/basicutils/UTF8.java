package io.schinzel.basicutils;

import java.nio.charset.StandardCharsets;

/**
 * The purpose of this class is to convert strings into UTF-8 byte arrays and vice versa.
 * Created by schinzel on 2017-04-30.
 */
public class UTF8 {

    /**
     * Private constructor as should not be instantiated.
     */
    private UTF8(){}

    /**
     * @param string
     * @return The argument string as UTF-8 byte array.
     */
    public static byte[] getBytes(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }


    /**
     * @param bytes
     * @return The argument UTF-8 encoded bytes as a string.
     */
    public static String getString(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
