package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;

/**
 * Purpose of this class is ...
 * <p>
 * Created by Schinzel on 2018-01-10
 */
public class FileReader {
    /**
     * @param fileName The name of a file
     * @return The file content as a string
     */
    public static String readAsString(String fileName) {
        File file = FileReader.getFile(fileName);
        return FileReader.readAsString(file);
    }


    /**
     * @param file A file
     * @return The file content as a string
     */
    @SneakyThrows
    public static String readAsString(File file) {
        Thrower.throwIfVarNull(file, "file");
        Thrower.throwIfFalse(file.isFile(), "Argument file '" + file.toString() + "' is not a file.");
        FileReader.throwIfDoesNotExist(file);
        return Files.asCharSource(file, Charsets.UTF_8).read();
    }


    /**
     * @param fileName The name of a file
     * @return The file content as a byte array
     */
    public static byte[] readAsByteArray(String fileName) {
        return FileReader.readAsByteArray(fileName, false);
    }


    /**
     * @param fileName         The name of a file
     * @param throwIOException If true and io exception is throw
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
     * @param fileName The name of a file
     * @return A file
     */
    static File getFile(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        File file = new File(fileName);
        FileReader.throwIfDoesNotExist(file);
        return file;
    }


    /**
     * Throw exception if the argument file does not exist
     *
     * @param file A file
     */
    static void throwIfDoesNotExist(File file) {
        Thrower.throwIfFalse(file.exists())
                .message("Error reading file. File '" + file.getName() + "' does not exist");
    }

}
