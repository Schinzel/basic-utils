package io.schinzel.basicutils.substringer;

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


    @Builder
    SubstringIndexFinder(String string, String subString, int occurrence, int startPos) {
        mSubstringPosition = getPos(string, subString, occurrence, startPos);
        mSubstringFound = (mSubstringPosition > -1);
    }


    static int getPos(String string, String subString, int occurrence, int startPos) {
        Thrower.throwIfTooSmall(occurrence, "occurrence", 1);
        Thrower.throwIfTooSmall(occurrence, "startPos", 0);
        int pos = string.indexOf(subString, startPos);
        if (pos == -1) {
            return -1;
        }
        if (occurrence == 1) {
            return pos;
        } else {
            return getPos(string, subString, --occurrence, pos + subString.length());
        }
    }
}
