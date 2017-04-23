package io.schinzel.basicutils.state;

import io.schinzel.basicutils.str.Str;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropDouble {
    private StateBuilder mStateBuilder;
    private String mKey;
    private double mDouble;


    /**
     * The number of digits in the fractional part of the rational number just added.
     *
     * @param decimals
     * @return
     */
    public PropUnit decimals(int decimals) {
        String valAsString = Str.create().a(mDouble, decimals).getString();
        return new PropUnit(mStateBuilder, mKey, valAsString, mDouble);
    }

}