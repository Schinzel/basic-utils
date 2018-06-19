package io.schinzel.basicutils.file;

import lombok.Builder;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class FileWriter3 {

    @Builder(buildMethodName = "write")
    FileWriter3(String fileName, String stringToWrite){
        FileWriterInternal.writeToFile(fileName, stringToWrite, FileWriterInternal.FileOp.WRITE);
    }





}
