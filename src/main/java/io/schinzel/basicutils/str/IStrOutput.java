package io.schinzel.basicutils.str;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * The purpose of this interface is to send the String held to different outputs such as system-out
 * and files.
 * <p>
 * Created by schinzel on 2017-02-27.
 */
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
        return this.writeToFile(fileName, false);
    }


    /**
     * Write the string held to a file with argument name.
     *
     * @param fileName The name of the file to write to.
     * @param append   If true append to file, else overwrite.
     * @return This for chaining.
     */
    default T writeToFile(String fileName, boolean append) {
        File file = new File(fileName);
        try {
            if (append) {
                Files.append(this.getString(), new File(fileName), IStr.ENCODING);
            } else {
                Files.write(this.getString(), new File(fileName), IStr.ENCODING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return this.getThis();
    }

}
