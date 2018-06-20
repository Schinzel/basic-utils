package io.schinzel.basicutils.file;

import com.google.common.io.Resources;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.IOException;
import java.net.URL;

/**
 * Purpose of this class is to read resource files.
 * <p>
 * All read operations are relative to the set resource directory. In tests all read operations
 * are relative to the test resource directory.
 * <p>
 * Created by Schinzel on 2018-06-20
 */
public class ResourceReader2 {


    /**
     * @param fileName The name of the resource file to read
     * @return The contents of the file
     */
    public static Bytes read(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        return ResourceReader2.read(fileName, false);
    }


    /**
     * @param fileName         The name of the file
     * @param throwIOException If true an io exception is thrown. For testing
     * @return The file content as a byte array
     */
    static Bytes read(String fileName, boolean throwIOException) {
        URL url = Resources.getResource(fileName);
        try {
            if (throwIOException) {
                throw new IOException();
            }
            byte[] byteArray = Resources.toByteArray(url);
            return new Bytes(byteArray);
        } catch (IOException e) {
            throw new RuntimeException("Error when reading resource file '" + fileName + "'. " + e.getMessage());
        }
    }


}
