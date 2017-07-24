package io.schinzel.basicutils.substring;

import io.schinzel.basicutils.Thrower;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to find the index of a string in another string.
 * <p>
 * Created by schinzel on 2017-03-25.
 */
@Accessors(prefix = "m")
class SubstringIndexFinder {
    /** True if the substring was found, else false. */
    @Getter private final boolean mSubstringFound;
    /** The index of the substring */
    @Getter private final int mSubstringPosition;
    /** Flag for last occurrence */
    static final int LAST_OCCURRENCE = Integer.MAX_VALUE;


    @Builder
    SubstringIndexFinder(String string, String subString, int occurrence, int startPos) {
        mSubstringPosition = getPos(string, subString, occurrence, startPos);
        mSubstringFound = (mSubstringPosition > -1);
    }


    /**
     * @param string     The string in which to look for the sub string
     * @param subString  The sub string to look after
     * @param occurrence The occurrence of the sub string
     * @param startPos   From which index to look for the sub string
     * @return The index of the argument sub string in the argument string. -1 if the substring was
     * not found.
     */
    static int getPos(String string, String subString, int occurrence, int startPos) {
        Thrower.throwIfVarTooSmall(occurrence, "occurrence", 1);
        Thrower.throwIfVarTooSmall(startPos, "startPos", 0);
        //If looking for the last occurrence
        int pos = (occurrence == LAST_OCCURRENCE)
                //Get the last index of the string
                ? string.lastIndexOf(subString)
                //Get the index of the sub string in the string
                : string.indexOf(subString, startPos);
        //If substring was not found
        if (pos == -1) {
            //Return not found flag
            return -1;
        }// else, i.e. the sub string was found
        else {
            //If this was the look after substring occurrence
            return (occurrence == 1 || occurrence == LAST_OCCURRENCE)
                    //Return this position
                    ? pos
                    //Do recursive request for the next occurrence of the substring
                    : getPos(string, subString, --occurrence, pos + subString.length());
        }
    }
}
