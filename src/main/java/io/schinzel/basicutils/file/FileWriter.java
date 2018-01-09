package io.schinzel.basicutils.file;


import com.google.common.base.Charsets;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.File;
import java.io.IOException;

/**
 * The purpose of this class is to write files.
 * <p>
 * Created by Schinzel on 2018-01-09
 */
public class FileWriter {
    File mFile;

    public static FileWriter create(String fileName){
        Thrower.throwIfVarEmpty(fileName, "fileName");

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
    static void writeToFile(String fileName, String stringToWrite, FileRW.FileOp fileOp) {
        if (stringToWrite == null) {
            stringToWrite = EmptyObjects.EMPTY_STRING;
        }
        try {
            File file = new File(fileName);
            //If should delete file on exit
            if (fileOp == FileRW.FileOp.DELETE_ON_EXIT) {
                file.deleteOnExit();
            }
            //If should append to file
            if (fileOp == FileRW.FileOp.APPEND) {
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
