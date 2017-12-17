package io.schinzel.basicutils.substring;

import io.schinzel.basicutils.thrower.Thrower;
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


    @Builder
    SubstringIndexFinder(String string, String subString, Occurrence occurrence, int startPos) {
        Thrower.throwIfVarTooSmall(startPos, "startPos", 0);
        //If this is a request for the last occurrence for the argument sub string
        mSubstringPosition = (occurrence == Occurrence.LAST)
                //Get index last of the last occurrence
                ? string.lastIndexOf(subString)
                //else, get the position of the requested occurrence
                : getPos(string, subString, occurrence.getPosition(), startPos);
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
    private int getPos(String string, String subString, int occurrence, int startPos) {
        Thrower.throwIfVarTooSmall(occurrence, "occurrence", 1);
        Thrower.throwIfVarTooSmall(startPos, "startPos", 0);
        //Get the index of the sub string in the string
        int pos = string.indexOf(subString, startPos);
        //If this was the requested sub string occurrence or the sub string was not found
        return (occurrence == 1 || pos == -1)
                //Return this position, which is a position or -1 for sub string not found flag
                ? pos
                //else, Do recursive request for the next occurrence of the substring
                : this.getPos(string, subString, --occurrence, pos + subString.length());
    }
}
