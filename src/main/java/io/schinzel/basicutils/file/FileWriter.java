package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.SneakyThrows;

import java.io.File;

/**
 * Purpose of this class is to write to files
 * <p>
 * Created by Schinzel on 2018-01-10
 */
public class FileWriter {

    /**
     * @param fileName      The name of a file
     * @param stringToWrite The file content
     */
    public static void append(String fileName, String stringToWrite) {
        writeToFile(fileName, stringToWrite, FileOp.APPEND);
    }


    /**
     * @param fileName      The name of a file
     * @param stringToWrite The file content
     */
    public static void write(String fileName, String stringToWrite) {
        writeToFile(fileName, stringToWrite, FileOp.WRITE);
    }


    /**
     * Writes to a file which is deleted when the JVM terminates. The file receives a random name
     * which is returned.
     *
     * @param stringToWrite The file stringToWrite
     * @return The name of the file written to
     */
    public static String writeToTempFile(String stringToWrite) {
        String fileName = FileWriter.class.getSimpleName()
                + "_" + RandomUtil.getRandomString(20) + ".txt";
        writeToFile(fileName, stringToWrite, FileOp.DELETE_ON_EXIT);
        return fileName;
    }


    /**
     * Writes to a file which is deleted when the JVM terminates.
     *
     * @param fileName      The name of a file
     * @param stringToWrite The file stringToWrite
     */
    public static void writeToTempFile(String fileName, String stringToWrite) {
        writeToFile(fileName, stringToWrite, FileOp.DELETE_ON_EXIT);
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
    @SneakyThrows
    static void writeToFile(String fileName, String stringToWrite, FileOp fileOp) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        if (stringToWrite == null) {
            stringToWrite = EmptyObjects.EMPTY_STRING;
        }
        File file = new File(fileName);
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
    }

}
