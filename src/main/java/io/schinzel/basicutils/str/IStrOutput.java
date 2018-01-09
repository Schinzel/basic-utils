package io.schinzel.basicutils.str;

import io.schinzel.basicutils.file.FileRW;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.thrower.Thrower;

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
    default T writeToSystemOut() {
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
    default T writeToSystemOutWithPrefix(String prefix) {
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
        Thrower.throwIfVarEmpty(fileName, "fileName");
        FileRW.write(fileName, this.getString());
        return this.getThis();
    }


    /**
     * Writes the string held to a temp file with a random name.
     *
     * @return The name of the temp file created.
     */
    default String writeToTempFile() {
        String fileName = RandomUtil.getRandomString(20) + ".txt";
        this.writeToTempFile(fileName);
        return fileName;
    }


    /**
     * Writes the string held to a file with the argument name.
     *
     * @param fileName The name of the file to write to
     * @return This for chaining.
     */
    default T writeToTempFile(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        FileRW.writeToTempFile(fileName, this.getString());
        return this.getThis();
    }


    /**
     * Appends the string held to a file with the argument name.
     *
     * @param fileName The name of the file to write to
     * @return This for chaining
     */
    default T appendToFile(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        FileRW.append(fileName, this.getString());
        return this.getThis();
    }


}
