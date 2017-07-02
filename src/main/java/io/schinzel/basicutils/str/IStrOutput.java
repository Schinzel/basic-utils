package io.schinzel.basicutils.str;

import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.RandomUtil;

import java.io.File;
import java.io.IOException;

/**
 * The purpose of this interface is to send the String held to different outputs such as system-out
 * and files.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
@SuppressWarnings("UnusedReturnValue")
interface IStrOutput<T extends IStr<T>> extends IStr<T> {

    /**
     * Print the string held to system out with a line break at the end.
     *
     * @return This for chaining.
     */
    default T pln() {
        System.out.println(this.getString());
        return this.getThis();
    }


    /**
     * Print the string held to system out prefixed with the argument prefix with a line break at
     * the end.
     *
     * @param prefix Prefix to print.
     * @return This for chaining.
     */
    default T plnWithPrefix(String prefix) {
        System.out.println(prefix + this.getString());
        return this.getThis();
    }


    /**
     * Write the string held to a file with argument name.
     *
     * @param fileName The name of the file to write to.
     * @return This for chaining.
     */
    default T writeToFile(String fileName) {
        IStrOutput.writeToFile(fileName, this.getString(), FileOp.NOTHING);
        return this.getThis();
    }


    /**
     * Writes the string held to a file with the argument name.
     *
     * @param fileName The name of the file to write to
     * @return This for chaining.
     */
    default T writeToTempFile(String fileName) {
        IStrOutput.writeToFile(fileName, this.getString(), FileOp.DELETE_ON_EXIT);
        return this.getThis();
    }


    /**
     * Writes the string held to a temp file with a random name.
     *
     * @return The name of the temp file created.
     */
    default String writeToTempFile() {
        String fileName = RandomUtil.getRandomString(10) + ".txt";
        IStrOutput.writeToFile(fileName, this.getString(), FileOp.DELETE_ON_EXIT);
        return fileName;
    }


    /**
     * Appends the string held to a file with the argument name.
     *
     * @param fileName The name of the file to write to
     * @return This for chaining
     */
    default T appendToFile(String fileName) {
        IStrOutput.writeToFile(fileName, this.getString(), FileOp.APPEND);
        return this.getThis();
    }


    /**
     * Different file operations
     */
    enum FileOp {
        NOTHING, APPEND, DELETE_ON_EXIT;


        /**
         * @param fileOps Zero, one or more file operations
         * @return True if this file operation is in the argument array. Else false.
         */
        boolean isIn(FileOp... fileOps) {
            if (Checker.isEmptyVarArg(fileOps)) {
                return false;
            }
            for (FileOp fileOp : fileOps) {
                if (fileOp == this) {
                    return true;
                }
            }
            return false;
        }
    }


    /**
     * Write the argument string to a file with argument name and perform the argument operations.
     *
     * @param fileName      The name of the file to write to.
     * @param stringToWrite The string to write to file
     * @param fileOps       One or several operations to carry out on file
     */
    static void writeToFile(String fileName, String stringToWrite, FileOp... fileOps) {
        try {
            File file = new File(fileName);
            //If should delete file on exit
            if (FileOp.DELETE_ON_EXIT.isIn(fileOps)) {
                file.deleteOnExit();
            }
            //If should append to file
            if (FileOp.APPEND.isIn(fileOps)) {
                Files.asCharSink(file, IStr.ENCODING, FileWriteMode.APPEND).write(stringToWrite);
            } //else write to file and overwrite and possible previous content
            else {
                Files.asCharSink(file, IStr.ENCODING).write(stringToWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
