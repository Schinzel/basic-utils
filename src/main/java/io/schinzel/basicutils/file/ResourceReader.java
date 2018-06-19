package io.schinzel.basicutils.file;

import com.google.common.io.Resources;
import io.schinzel.basicutils.UTF8;
import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.IOException;
import java.net.URL;

/**
 * Purpose of this class is to read resource files
 * <p>
 * Created by Schinzel on 2018-01-13
 */
public class ResourceReader {


    public static String read(String fileName) {
        byte[] bytes = ResourceReader.readAsByteArrayInternal(fileName, false);
        return UTF8.getString(bytes);
    }


    @SuppressWarnings("WeakerAccess")
    public static Str readAsStr(String fileName) {
        String string = ResourceReader.read(fileName);
        return Str.create(string);
    }


    @SuppressWarnings("WeakerAccess")
    public static byte[] readAsByteArray(String fileName) {
        return ResourceReader.readAsByteArrayInternal(fileName, false);
    }


    static byte[] readAsByteArrayInternal(String fileName, boolean throwIOException) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        URL url = Resources.getResource(fileName);
        try {
            if (throwIOException) {
                throw new IOException();
            }
            return Resources.toByteArray(url);
        } catch (IOException e) {
            throw new RuntimeException("Error when reading resource file '" + fileName + "'. " + e.getMessage());
        }
    }


}
