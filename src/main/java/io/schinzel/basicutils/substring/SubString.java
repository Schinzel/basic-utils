package io.schinzel.basicutils.substring;

import io.schinzel.basicutils.Thrower;
import io.schinzel.basicutils.str.Str;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to extract substring from a string between a start and end string.
 *
 * @author Schinzel
 */
@Accessors(prefix = "m")
public class SubString {
    /** Flag that indicates that no value has been set. */
    private static final String NO_VAL_SET = "qEvLh6L7HJ6uAkoJB7kT";
    /** Extracted substring */
    @Getter(AccessLevel.PACKAGE) private final String mString;


    public static Builder create(String string) {
        return new Builder(string);
    }


    @Accessors(prefix = "m", fluent = true, chain = true)
    public static class Builder {
        private final String mString;
        @Setter private String mStartDelimiter = NO_VAL_SET;
        @Setter private int mStartOccurrence = 1;
        @Setter private String mEndDelimiter = NO_VAL_SET;
        @Setter private int mEndOccurrence = 1;


        Builder(String string) {
            mString = string;
        }


        /**
         *
         * @return The requested substring as string.
         */
        public String getString() {
            return new SubString(this).getString();
        }


        /**
         * @return The sub-string as a Str.
         */
        public Str getStr() {
            return Str.create(this.getString());
        }


        /**
         *
         * @return The request substring as new SubString.
         */
        public Builder newSubString() {
            return SubString.create(this.getString());
        }

    }


    private SubString(Builder builder) {
        String string = builder.mString;
        Thrower.throwIfVarNull(string, "string");
        int startPos = getStartPos(string, builder.mStartDelimiter, builder.mStartOccurrence);
        int endPos = getEndPos(string, builder.mEndDelimiter, builder.mEndOccurrence, startPos);
        mString = string.substring(startPos, endPos);

    }


    static int getStartPos(String string, String startDelimiter, int startOccurrence) {
        //If no start delimiter has been set
        if (!valueSet(startDelimiter)) {
            //Let the start pos be start of string.
            return 0;
        }
        SubstringIndexFinder startIndex = SubstringIndexFinder.builder()
                .string(string)
                .subString(startDelimiter)
                .occurrence(startOccurrence)
                .build();
        return startIndex.isSubstringFound()
                ? startIndex.getSubstringPosition() + startDelimiter.length()
                : string.length();
    }


    static int getEndPos(String string, String endDelimiter, int endOccurrence, int startPos) {
        //If not end delimiter has been set
        if (!valueSet(endDelimiter)) {
            //Let the end pos be end of string
            return string.length();
        }
        SubstringIndexFinder endIndex = SubstringIndexFinder.builder()
                .string(string)
                .startPos(startPos)
                .subString(endDelimiter)
                .occurrence(endOccurrence)
                .build();
        return endIndex.isSubstringFound()
                ? endIndex.getSubstringPosition()
                : string.length();
    }


    /**
     * @param delimiter The delimiter to check.
     * @return True if no value has been set for argument delimiter
     */
    private static boolean valueSet(String delimiter) {
        return !delimiter.equals(NO_VAL_SET);
    }


}
