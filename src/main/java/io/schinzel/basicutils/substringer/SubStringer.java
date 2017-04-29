package io.schinzel.basicutils.substringer;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.Thrower;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to extract substring from a string between a start and end string.
 *
 * @author Schinzel
 */
@Accessors(prefix = "m")
public class SubStringer {
    /** Flag that indicates that no value has been set. */
    private static final String NO_VAL_SET = "qEvLh6L7HJ6uAkoJB7kT";
    /** Extracted substring */
    @Getter private final String mSubString;


    @Builder
    private SubStringer(String string, String startDelimiter, int startOccurrence, String endDelimiter, int endOccurrence) {
        Thrower.throwIfVarNull(string, "string");
        //If start delimiter was not set (or was set to null or empty string)
        if (Checker.isEmpty(startDelimiter)) {
            startDelimiter = NO_VAL_SET;
        }
        //If start delimiter was not set (or was set to null or empty string)
        if (Checker.isEmpty(endDelimiter)) {
            endDelimiter = NO_VAL_SET;
        }
        int startPos = getStartPos(string, startDelimiter, startOccurrence);
        int endPos = getEndPos(string, endDelimiter, endOccurrence, startPos);
        mSubString = string.substring(startPos, endPos);

    }
    /**
     * To Do:
     * - Byta namn till SubString?
     * - Hur används contains-metoden som nog måste bort.
     * - Tester
     * - Få resten av projekt att funkar
     * - Sample
     * - Read me: Release notes
     * - Read me: sample
     */


    /**
     * @return A new sub-stringer builder for extracting a string from an extracted string.
     */
    public SubStringer.SubStringerBuilder getSubStringer() {
        return SubStringer.builder().string(this.getSubString());
    }


    /**
     * @param s The string to look for
     * @return True if constructor set string contains argument string.
     */
    public boolean contains(String s) {
        return this.getSubString().contains(s);
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
