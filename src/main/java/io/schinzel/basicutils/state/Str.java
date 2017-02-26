package io.schinzel.basicutils.state;

import java.util.Locale;

/**
 * The purpose of this class is to offer a less verbose version of a
 * StringBuilder. For internal usage only for now.
 *
 * @author schinzel
 */
class Str {
    /**
     * Locale used to transform numbers to strings.
     */
    private static final Locale STR_LOCALE = Locale.US;

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


        public String toString() {
            return mWhitespace;
        }
    }

    /**
     * Holds the string itself.
     */
    StringBuilder mSb = new StringBuilder();


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
        mSb.append(s);
        return this;
    }


    /**
     * Append the argument str.
     *
     * @param s The str to append.
     * @return This for chaining.
     */
    public Str a(Str s) {
        mSb.append(s.mSb);
        return this;
    }
    //------------------------------------------------------------------------
    // WHITE SPACES
    //------------------------------------------------------------------------


    /**
     * Append a white space.
     *
     * @param whitespace The white space to append.
     * @return This for chaining.
     */
    public Str aws(WS whitespace) {
        mSb.append(whitespace.toString());
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
    //------------------------------------------------------------------------
    // NUMBERS
    //------------------------------------------------------------------------


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param f             The float to add
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    public Str a(float f, int numOfDecimals) {
        return this.a((double) f, numOfDecimals);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param d             The double to add
     * @param numOfDecimals The number of decimals to display.
     * @return This for chaining,
     */
    public Str a(double d, int numOfDecimals) {
        String s = String.format(STR_LOCALE, "%,." + numOfDecimals + "f", d);
        mSb.append(s);
        return this;
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to add.
     * @return This for chaining.
     */
    public Str a(int l) {
        return this.a((long) l);
    }


    /**
     * The argument value is added with a thousand separator for readability.
     *
     * @param l The long to add.
     * @return This for chaining.
     */
    public Str a(long l) {
        String s = String.format(STR_LOCALE, "%,d", l);
        mSb.append(s);
        return this;
    }
    //------------------------------------------------------------------------
    // OUTPUT
    //------------------------------------------------------------------------


    /**
     * @return The string held.
     */
    @Override
    public String toString() {
        return mSb.toString();
    }


    /**
     * Print the string held to system out.
     *
     * @return This for chaining.
     */
    public Str pln() {
        System.out.println(mSb.toString());
        return this;
    }

}
