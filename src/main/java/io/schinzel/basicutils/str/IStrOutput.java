package io.schinzel.basicutils.str;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * The purpose of this interface is to send the String held to different outputs such as system-out and files.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
interface IStrOutput<T extends IStr<T>> extends IStr<T> {


    /**
     * Print the string held to system out.
     *
     * @return This for chaining.
     */
    default T pln() {
        System.out.println(this.getString());
        return this.getThis();
    }


    /**
     * Write the string held to a file with argument name.
     *
     * @param fileName The name of the file to write to.
     * @return This for chaining.
     */
    default T writeToFile(String fileName) {
        return this.writeToFile(fileName, false);
    }


    /**
     * Write the string held to a file with argument name.
     *
     * @param fileName The name of the file to write to.
     * @param append   If {@true} append to file, else overwrite.
     * @return This for chaining.
     */
    default T writeToFile(String fileName, boolean append) {
        File file = new File(fileName);
        try {
            FileUtils.writeStringToFile(file, this.getString(), Charset.forName("UTF-8"), append);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return this.getThis();
    }

}
