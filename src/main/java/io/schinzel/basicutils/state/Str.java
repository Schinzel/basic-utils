package io.schinzel.basicutils.state;

/**
 * The purpose of this class is to offer a less verbose version of a
 * StringBuilder. For internal usage only for now.
 *
 * @author schinzel
 */
class Str {

    /**
     * Enum for whitespaces.
     */
    public enum WS {
        NL("\n"),
        SPACE(" "),
        TAB("\t");

        private final String mWhitespace;


        WS(String whitespace) {
            mWhitespace = whitespace;
        }

        public String toString(){
            return mWhitespace;
        }
    }

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
     * Append a white space.
     *
     * @param whitespace The white space to append.
     * @return This for chaining.
     */
    public Str aws(WS whitespace) {
        sb.append(whitespace.toString());
        return this;
    }


    /**
     * Append a newline.
     *
     * @return This for chaining.
     */
    public Str anl() {
        return this.aws(WS.NL);
    }


    /**
     * Append a space.
     *
     * @return This for chaining.
     */
    public Str asp() {
        return this.aws(WS.SPACE);
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
     * @return The string held.
     */
    @Override
    public String toString() {
        return sb.toString();
    }

}
