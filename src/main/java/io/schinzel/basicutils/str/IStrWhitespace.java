package io.schinzel.basicutils.str;

/**
 * The purpose of this interface is to append whitespaces.
 *
 * Created by schinzel on 2017-02-26.
 */
interface IStrWhitespace<T extends IStr<T>> extends IStr<T>  {
    /**s
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


        public String toString() {
            return mWhitespace;
        }
    }


    /**
     * Append a white space.
     *
     * @param whitespace The white space to append.
     * @return This for chaining.
     */
    default T aws(WS whitespace) {
        this.append(whitespace.toString());
        return this.getThis();
    }


    /**
     * Append a newline.
     *
     * @return This for chaining.
     */
    default T anl() {
        return this.aws(WS.NL);
    }


    /**
     * Append a space.
     *
     * @return This for chaining.
     */
    default T asp() {
        return this.aws(WS.SPACE);
    }
}
