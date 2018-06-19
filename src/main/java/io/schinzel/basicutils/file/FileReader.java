package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

/**
 * Purpose of this class is to read files
 * <p>
 * All read operations are relative to the set working directory.
 * <p>
 * Created by Schinzel on 2018-01-10
 */
public class FileReader {

    /**
     * Reads a file. UTF-8 encoding is assumed.
     *
     * @param fileName The name of the file
     * @return The file content as a string
     */
    public static String read(String fileName) {
        File file = FileReader.getFile(fileName);
        return FileReader.read(file);
    }


    /**
     * Reads a file. UTF-8 encoding is assumed.
     *
     * @param fileName The name of the file
     * @return The file content
     */
    public static Str readAsStr(String fileName) {
        String fileContent = FileReader.read(fileName);
        return Str.create(fileContent);
    }


    /**
     * Reads a file. UTF-8 encoding is assumed.
     *
     * @param file A file
     * @return The file content as a string
     */
    @SneakyThrows
    public static String read(File file) {
        validateFile(file);
        return Files.asCharSource(file, Charsets.UTF_8).read();
    }


    /**
     * @param fileName The name of the file
     * @return The file content as a byte array
     */
    @SuppressWarnings("WeakerAccess")
    public static byte[] readAsByteArray(String fileName) {
        return FileReader.readAsByteArray(fileName, false);
    }


    /**
     * @param fileName         The name of the file
     * @param throwIOException If true an io exception is thrown. For testing
     * @return The file content as a byte array
     */
    static byte[] readAsByteArray(String fileName, boolean throwIOException) {
        File file = FileReader.getFile(fileName);
        try {
            if (throwIOException) {
                throw new IOException("Emulated io exception");
            }
            return Files.asByteSource(file).read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param fileName The name of the file
     * @return A file
     */
    private static File getFile(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        File file = new File(fileName);
        validateFile(file);
        return file;
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
