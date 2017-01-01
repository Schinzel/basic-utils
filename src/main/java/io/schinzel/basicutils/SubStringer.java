package io.schinzel.basicutils;

/**
 * The purpose of this class is to extract substring from a string between
 * a start and end string.
 *
 * @author Schinzel
 */
public class SubStringer {
    /**
     * Flag that indicates that no value has been set.
     */
    private static final String NO_VAL_SET = "qEvLh6L7HJ6uAkoJB7kT";
    /**
     * The string from which to extract a substring.
     */
    private final String mStr;
    /**
     * The start delimiter string.
     */
    private String mStartDelimiter = NO_VAL_SET;
    /**
     * The end delimiter string.
     */
    private String mEndDelimiter = NO_VAL_SET;


    /**
     * Private constructor so that is not used directly.
     *
     * @param str
     */
    private SubStringer(String str) {
        mStr = str;
    }


    /**
     *
     * @param str The string from which to extract a substring.
     * @return An instance.
     */
    public static SubStringer create(String str) {
        return new SubStringer(str);
    }


    /**
     *
     * @param delimiter Sets the start delimiter. The first occurrence of this
     * string is used as delimiter, exclusive this string.
     * @return This, for chaining.
     */
    public SubStringer start(String delimiter) {
        if (Checker.isEmpty(delimiter)) {
            throw new RuntimeException("Start delimiter cannot be set to empty value");
        }
        if (valueSet(mStartDelimiter)) {
            throw new RuntimeException("Start delimiter already set once");
        }
        mStartDelimiter = delimiter;
        return this;
    }


    /**
     *
     * @param delimiter Sets the end delimiter. The first occurrence of this
     * string after the start string, is the end of the substring, exclusive
     * this string.
     * @return This, for chaining.
     */
    public SubStringer end(String delimiter) {
        if (Checker.isEmpty(delimiter)) {
            throw new RuntimeException("End delimiter cannot be set to empty value");
        }
        if (valueSet(mEndDelimiter)) {
            throw new RuntimeException("End delimiter already set once");
        }
        mEndDelimiter = delimiter;
        return this;
    }


    /**
     *
     * @param delimiter
     * @return True if no value has been set for argument delimiter
     */
    private static boolean valueSet(String delimiter) {
        return !delimiter.equals(NO_VAL_SET);
    }


    private static int getStartPos(String str, String delimiter) {
        //If no start pos
        if (!valueSet(delimiter)) {
            //Let start be start of string
            return 0;
        }
        int startPos = str.indexOf(delimiter);
        //If start delimiter was not found
        if (startPos == -1) {
            //Let start be end of string
            return str.length();
        } else {
            startPos += delimiter.length();
        }
        return startPos;
    }


    private static int getEndPos(String str, String delimiter, int startPos) {
        //If no end pos
        if (!valueSet(delimiter)) {
            //Let end be end of string
            return str.length();
        }
        int endPos = str.indexOf(delimiter, startPos);
        //If end delimiter was not found
        if (endPos == -1) {
            //Let end be end of string
            return str.length();
        }
        return endPos;
    }


    /**
     *
     * @return A new substring which takes the generated substring as input.
     */
    public SubStringer getSubStringer() {
        return new SubStringer(this.toString());
    }


    /**
     *
     * @return The substring between the start and end delimiter, exclusive the
     * delimiters. If no start delimiter has been set, start of string is
     * assumed. If no end delimiter has been set, end of string is assumed.
     */
    @Override
    public String toString() {
        //If no start nor end pos
        if (!valueSet(mStartDelimiter) && !valueSet(mEndDelimiter)) {
            throw new RuntimeException("Incorrect usage. Neither start nor end delimiter has been set.");
        }
        int startPos = getStartPos(mStr, mStartDelimiter);
        int endPos = getEndPos(mStr, mEndDelimiter, startPos);
        return mStr.substring(startPos, endPos);
    }

}
