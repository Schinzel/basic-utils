package io.schinzel.basicutils.file;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.RandomUtil;
import lombok.Builder;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class FileWriter4 {


    @Builder(builderClassName = "WriterBuilder", builderMethodName = "writer", buildMethodName = "write")
    static void write(String fileName, String stringToWrite) {
        FileWriterInternal.writeToFile(fileName, stringToWrite, FileWriterInternal.FileOp.WRITE);
    }


    @Builder(builderClassName = "AppenderBuilder", builderMethodName = "appender", buildMethodName = "append")
    static void append(String fileName, String stringToWrite) {
        FileWriterInternal.writeToFile(fileName, stringToWrite, FileWriterInternal.FileOp.APPEND);
    }


    @Builder(builderClassName = "TempFileWriterBuilder", builderMethodName = "tempFileWriter", buildMethodName = "write")
    static String writeToTempFile(String fileName, String stringToWrite) {
        fileName = Checker.isNotEmpty(fileName)
                ? fileName
                : FileWriter4.class.getSimpleName() + "_" + RandomUtil.getRandomString(20) + ".txt";
        FileWriterInternal.writeToFile(fileName, stringToWrite, FileWriterInternal.FileOp.APPEND);
        return fileName;
    }
}
