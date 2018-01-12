package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.File;
import java.io.IOException;

/**
 * Purpose of this class is ...
 * <p>
 * Created by Schinzel on 2018-01-10
 */
public class FileReader2 {
    /**
     * @param fileName The name of a file
     * @return The file content as a string
     */
    public static String readAsString(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        File file = new File(fileName);
        return FileRW.readAsString(file);
    }


    /**
     * @param file A file
     * @return The file content as a string
     */
    public static String readAsString(File file) {
        Thrower.throwIfVarNull(file, "file");
        FileRW.throwIfDoesNotExist(file);
        try {
            return Files.asCharSource(file, Charsets.UTF_8).read();
        } catch (IOException e) {
            throw new RuntimeException("Problems when reading file '" + file.getName() + "'. " + e.getMessage());
        }
    }


    /**
     * @param fileName The name of a file
     * @return The file content as a byte array
     */
    public static byte[] readAsByteArray(String fileName) {
        File file = new File(fileName);
        FileRW.throwIfDoesNotExist(file);
        try {
            return Files.toByteArray(file);
        } catch (IOException e) {
            throw new RuntimeException("Problems when reading file '" + fileName + "'. " + e.getMessage());
        }
    }
}
