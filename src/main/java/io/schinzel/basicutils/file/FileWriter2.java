package io.schinzel.basicutils.file;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.RandomUtil;
import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * The purpose of this class
 *
 * @author Schinzel
 */
@Accessors(prefix = "m")
@Builder
public class FileWriter2 {
    String mFileName;
    String mStringToWrite;


    public void write() {
        FileWriterInternal.writeToFile(mFileName, mStringToWrite, FileWriterInternal.FileOp.WRITE);
    }


    public void append() {
        FileWriterInternal.writeToFile(mFileName, mStringToWrite, FileWriterInternal.FileOp.APPEND);
    }


    public String writeToTempFile() {
        mFileName = Checker.isNotEmpty(mFileName)
                ? mFileName
                : FileWriter4.class.getSimpleName() + "_" + RandomUtil.getRandomString(20) + ".txt";
        FileWriterInternal.writeToFile(mFileName, mStringToWrite, FileWriterInternal.FileOp.WRITE);
        return mFileName;
    }


}
