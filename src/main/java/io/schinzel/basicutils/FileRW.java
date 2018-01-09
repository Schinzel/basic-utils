package io.schinzel.basicutils;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.File;
import java.io.IOException;

/**
 * The purpose of this class is to read from and write to files.
 * <p>
 * Created by Schinzel on 2018-01-09
 */
public class FileRW {


    /**
     * @param fileName The name of a file
     * @return The file content as a string
     */
    public static String readAsString(String fileName) {
        File file = FileRW.getFile(fileName);
        return FileRW.readAsString(file);
    }


    /**
     * @param file A file
     * @return The file content as a string
     */
    public static String readAsString(File file) {
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
        File file = FileRW.getFile(fileName);
        try {
            return Files.toByteArray(file);
        } catch (IOException e) {
            throw new RuntimeException("Problems when reading file '" + fileName + "'. " + e.getMessage());
        }
    }


    /**
     * @param fileName The name of a file
     * @param content  The file content
     */
    public static void append(String fileName, String content) {
        writeToFile(fileName, content, FileOp.APPEND);
    }


    /**
     * @param fileName The name of a file
     * @param content  The file content
     */
    public static void write(String fileName, String content) {
        writeToFile(fileName, content, FileOp.WRITE);
    }


    /**
     * @param fileName The name of a file
     * @param content  The file content
     */
    public static void writeToTempFile(String fileName, String content) {
        writeToFile(fileName, content, FileOp.DELETE_ON_EXIT);
    }


    /**
     * @param fileName The name of a file
     * @return A new instance with the argument file name
     */
    static File getFile(String fileName) {
        File file = new File(fileName);
        Thrower.throwIfFalse(!file.exists(), "No such file '" + fileName + "'");
        return file;
    }


    /**
     * Different file operations
     */
    enum FileOp {
        WRITE, APPEND, DELETE_ON_EXIT
    }


    /**
     * Write the argument string to a file with argument name and perform the argument operation.
     *
     * @param fileName      The name of the file to write to.
     * @param stringToWrite The string to write to file
     * @param fileOp        The operations to carry out on file
     */
    static void writeToFile(String fileName, String stringToWrite, FileOp fileOp) {
        try {
            File file = FileRW.getFile(fileName);
            //If should delete file on exit
            if (fileOp == FileOp.DELETE_ON_EXIT) {
                file.deleteOnExit();
            }
            //If should append to file
            if (fileOp == FileOp.APPEND) {
                Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND).write(stringToWrite);
            } //else write to file and overwrite possible previous content
            else {
                Files.asCharSink(file, Charsets.UTF_8).write(stringToWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
