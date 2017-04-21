package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to append whitespaces.
 * <p>
 * Created by Schinzel on 2017-02-26.
 */
interface IStrWhitespace<T extends IStr<T>> extends IStr<T> {
    /**
     * Enum for whitespaces.
     */
    enum WS {
        LF("\n"),
        CR_LF("\r\n"),
        SPACE(" "),
        TAB("\t");

        private final String mWhitespace;


        WS(String whitespace) {
            mWhitespace = whitespace;
        }


        @Override
        public String toString() {
            return mWhitespace;
        }
    }


    /**
     * Append a white space.
     *
     * @param whitespace The white space to a.
     * @return This for chaining.
     */
    default T aws(WS whitespace) {
        this.a(whitespace.toString());
        return this.getThis();
    }


    /**
     * Append a white space.
     *
     * @param whitespace The white space to a.
     * @return This for chaining.
     */
    default T aws(WS whitespace, String s) {
        this.a(s);
        this.a(whitespace.toString());
        return this.getThis();
    }


    /**
     * Append a newline.
     *
     * @return This for chaining.
     */
    default T anl() {
        return this.aws(WS.LF);
    }


    /**
     * Appends argument string followed by a newline.
     *
     * @param s The string to a append.
     * @return This for chaining.
     */
    default T anl(String s) {
        return this.aws(WS.LF, s);
    }


    /**
     * Append a space.
     *
     * @return This for chaining.
     */
    default T asp() {
        return this.aws(WS.SPACE);
    }


    /**
     * Appends argument string followed by a space.
     *
     * @param s The string to a append.
     * @return This for chaining.
     */
    default T asp(String s) {
        return this.aws(WS.SPACE, s);
    }


    /**
     * Append a tab.
     *
     * @return This for chaining.
     */
    default T atab() {
        return this.aws(WS.TAB);
    }


    /**
     * Appends argument string followed by a space.
     *
     * @param s The string to a append.
     * @return This for chaining.
     */
    default T atab(String s) {
        return this.aws(WS.TAB, s);
    }
}
