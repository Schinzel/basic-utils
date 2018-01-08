package io.schinzel.basicutils.substring;

import io.schinzel.basicutils.str.Str;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m", fluent = true, chain = true)
public class SubStringBuilder {
    /** Flag that indicates that no value has been set. */
    private static final String NO_VAL_SET = "qEvLh6L7HJ6uAkoJB7kT";
    final String mString;
    @Setter String mStartDelimiter = NO_VAL_SET;
    Occurrence mStartDelimiterOccurrence = Occurrence.FIRST;
    @Setter String mEndDelimiter = NO_VAL_SET;
    Occurrence mEndDelimiterOccurrence = Occurrence.FIRST;


    SubStringBuilder(String string) {
        mString = string;
    }


    public SubStringBuilder startDelimiter(String delimiter, Occurrence occurrence) {
        mStartDelimiter = delimiter;
        mStartDelimiterOccurrence = occurrence;
        return this;
    }


    public SubStringBuilder endDelimiter(String delimiter, Occurrence occurrence) {
        mEndDelimiter = delimiter;
        mEndDelimiterOccurrence = occurrence;
        return this;
    }


    /**
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
     * @return The requested substring as string.
     */
    public String toString() {
        return this.getString();
    }


    /**
     * @return The request substring as new SubString.
     */
    public SubStringBuilder newSubString() {
        return SubString.create(this.getString());
    }
}
