package io.schinzel.basicutils.file;

import lombok.Builder;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
public class FileAppender {

    @Builder(buildMethodName = "append")
    FileAppender(String fileName, String stringToWrite){
        FileWriterInternal.writeToFile(fileName, stringToWrite, FileWriterInternal.FileOp.APPEND);
    }

}
