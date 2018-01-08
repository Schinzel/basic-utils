package io.schinzel.basicutils.substring;

import io.schinzel.basicutils.thrower.Thrower;
import lombok.AccessLevel;
import lombok.Getter;
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


    public static SubStringBuilder create(String string) {
        return new SubStringBuilder(string);
    }


    SubString(SubStringBuilder builder) {
        String string = builder.mString;
        Thrower.throwIfVarNull(string, "string");
        int startPos = getStartPos(string, builder.mStartDelimiter, builder.mStartDelimiterOccurrence);
        int endPos = getEndPos(string, builder.mEndDelimiter, builder.mEndDelimiterOccurrence, startPos);
        mString = string.substring(startPos, endPos);

    }


    private static int getStartPos(String string, String startDelimiter, Occurrence startOccurrence) {
        //If no start delimiter has been set
        if (!valueSet(startDelimiter)) {
            //Let the start pos be start of string.
            return 0;
        }
        SubStringIndexFinder startIndex = SubStringIndexFinder.builder()
                .string(string)
                .subString(startDelimiter)
                .occurrence(startOccurrence)
                .build();
        return startIndex.isSubstringFound()
                ? startIndex.getSubstringPosition() + startDelimiter.length()
                : string.length();
    }


    private static int getEndPos(String string, String endDelimiter, Occurrence endOccurrence, int startPos) {
        //If not end delimiter has been set
        if (!valueSet(endDelimiter)) {
            //Let the end pos be end of string
            return string.length();
        }
        SubStringIndexFinder endIndex = SubStringIndexFinder.builder()
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
