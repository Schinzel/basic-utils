package io.schinzel.basicutils.substringer;

import io.schinzel.basicutils.Thrower;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to extract substring from a string between
 * a start and end string.
 *
 * @author Schinzel
 */
@Accessors(prefix = "m", chain = true, fluent = true)
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
    @Setter
    private String mStartDelimiter = NO_VAL_SET;
    /**
     * Which occurrence of the start delimiter to look for
     */
    @Setter
    private int mStartOccurrence = 1;
    /**
     * The end delimiter string.
     */
    @Setter
    private String mEndDelimiter = NO_VAL_SET;
    /**
     * Which occurrence of the end delimiter to look for
     */
    @Setter
    private int mEndOccurrence = 1;


    /**
     * Private constructor so that is not used directly.
     *
     * @param str The string from which to extract a substring.
     */
    private SubStringer(String str) {
        Thrower.throwIfNull(str, "str");
        mStr = str;
    }


    /**
     * @param str The string from which to extract a substring.
     * @return An instance.
     */
    public static SubStringer create(String str) {
        return new SubStringer(str);
    }


    /**
     * @param delimiter The delimiter to check.
     * @return True if no value has been set for argument delimiter
     */
    private static boolean valueSet(String delimiter) {
        return !delimiter.equals(NO_VAL_SET);
    }


    /**
     * @return A new substring which takes the generated substring as input.
     */
    public SubStringer getSubStringer() {
        return new SubStringer(this.toString());
    }


    /**
     * @param s The string to look for,
     * @return True if constructor set string contains argument string.
     */
    public boolean contains(String s) {
        return mStr.contains(s);
    }


    int getStartPos() {
        //If no start delimiter has been set
        if (!valueSet(mStartDelimiter)) {
            //Let the start pos be start of string.
            return 0;
        }
        SubstringIndexFinder startIndex = SubstringIndexFinder.builder()
                .string(mStr)
                .subString(mStartDelimiter)
                .occurrence(mStartOccurrence)
                .build();
        return startIndex.isSubstringFound()
                ? startIndex.getSubstringPosition() + mStartDelimiter.length()
                : mStr.length();
    }


    int getEndPos(int startPos) {
        //If not end delimiter has been set
        if (!valueSet(mEndDelimiter)) {
            //Let the end pos be end of string
            return mStr.length();
        }
        SubstringIndexFinder endIndex = SubstringIndexFinder.builder()
                .string(mStr)
                .startPos(startPos)
                .subString(mEndDelimiter)
                .occurrence(mEndOccurrence)
                .build();
        return endIndex.isSubstringFound()
                ? endIndex.getSubstringPosition()
                : mStr.length();
    }


    /**
     * @return The substring between the start and end delimiter, exclusive the
     * delimiters. If no start delimiter has been set, start of string is
     * assumed. If no end delimiter has been set, end of string is assumed.
     */
    @Override
    public String toString() {
        //Throw exception if either start or end delimiter has been set to empty value.
        Thrower.throwIfEmpty(mStartDelimiter, "StartDelimiter");
        Thrower.throwIfEmpty(mEndDelimiter, "EndDelimiter");
        int startPos = this.getStartPos();
        int endPos = this.getEndPos(startPos);
        return mStr.substring(startPos, endPos);
    }
}
