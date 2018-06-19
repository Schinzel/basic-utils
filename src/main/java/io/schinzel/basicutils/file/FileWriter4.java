package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.Builder;
import lombok.SneakyThrows;

import java.io.File;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class FileWriter4 {


    @Builder(builderClassName = "WriterBuilder", builderMethodName = "writer", buildMethodName = "write")
    static void write(String fileName, String stringToWrite) {
        FileWriter4.writeToFile(fileName, stringToWrite, FileOp.WRITE);
    }


    @Builder(builderClassName = "AppenderBuilder", builderMethodName = "appender", buildMethodName = "append")
    static void append(String fileName, String stringToWrite) {
        FileWriter4.writeToFile(fileName, stringToWrite, FileOp.APPEND);
    }


    @Builder(builderClassName = "TempFileWriterBuilder", builderMethodName = "tempFileWriter", buildMethodName = "write")
    static String writeToTempFile(String fileName, String stringToWrite) {
        fileName = Checker.isNotEmpty(fileName)
                ? fileName
                : FileWriter4.class.getSimpleName() + "_" + RandomUtil.getRandomString(20) + ".txt";
        FileWriter4.writeToFile(fileName, stringToWrite, FileOp.DELETE_ON_EXIT);
        return fileName;
    }


    /**
     * File operations
     */
    enum FileOp {
        WRITE, APPEND, DELETE_ON_EXIT
    }


    /**
     * Write the argument string to a file with argument name and perform the argument operation.
     *
     * @param fileName      The name of the file to write to
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
