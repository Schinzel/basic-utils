package io.schinzel.basicutils.file;


import com.google.common.io.Files;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.File;
import java.io.IOException;

/**
 * Purpose of this class is to read files
 * <p>
 * All read operations are relative to the set working directory.
 * <p>
 * Created by Schinzel on 2018-06-19
 */
public class FileReader {

    /**
     * Reads a file.
     *
     * @param fileName The name of the file
     * @return The contents of the file
     */
    public static Bytes read(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        File file = new File(fileName);
        return FileReader.read(file);
    }


    /**
     * Reads a file.
     *
     * @param file The file to read
     * @return The contents of the file
     */
    public static Bytes read(File file) {
        return FileReader.read(file, false);
    }


    /**
     * @param file             The file to read
     * @param throwIOException If true an io exception is thrown. For testing
     * @return The file content
     */
    static Bytes read(File file, boolean throwIOException) {
        validateFile(file);
        try {
            if (throwIOException) {
                throw new IOException("Emulated io exception");
            }
            byte[] byteArray = Files.asByteSource(file).read();
            return new Bytes(byteArray);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Validates argument file so is not null, does exist and is a file.
     *
     * @param file File to validate
     */
    static void validateFile(File file) {
        Thrower.throwIfVarNull(file, "file");
        Thrower.throwIfFalse(file.exists()).message("Error reading file. File '%s' does not exist.", file.getName());
        Thrower.throwIfFalse(file.isFile()).message("Error reading file. Argument file '%s' is not a file.", file.toString());
    }

}
