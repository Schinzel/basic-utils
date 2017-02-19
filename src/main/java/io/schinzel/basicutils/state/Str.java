package io.schinzel.basicutils.state;

/**
 * The purpose of this class is to offer a less verbose version of a
 * StringBuilder. For internal usage only for now.
 *
 * @author schinzel
 */
public class Str {

    /**
     * New line delimiter.
     */
    final static String NEW_LINE = "\n";
    /**
     * Holds the string itself.
     */
    StringBuilder sb = new StringBuilder();


    /**
     * Package private constructor so that people will use create-method.
     */
    Str() {
    }


    /**
     *
     * @return A new Str instance.
     */
    static Str create() {
        return new Str();
    }


    /**
     * Append the argument string.
     *
     * @param s The string to append.
     * @return This for chaining.
     */
    public Str a(String s) {
        sb.append(s);
        return this;
    }


    /**
     * Append the argument str.
     *
     * @param s The str to append.
     * @return This for chaining.
     */
    public Str a(Str s) {
        sb.append(s.sb);
        return this;
    }


    /**
     * Append a new-line.
     *
     * @return This for chaining.
     */
    public Str nl() {
        sb.append(NEW_LINE);
        return this;
    }


    /**
     * Print the string held to system out.
     *
     * @return This for chaining.
     */
    public Str pln() {
        System.out.println(sb.toString());
        return this;
    }


    /**
     *
     * @return The string held.
     */
    @Override
    public String toString() {
        return sb.toString();
    }

}
