package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class ADouble {
    private StateBuilder mStateBuilder;
    private String mKey;
    private double mDouble;


    public Unit decimals(int decimals) {
        String valAsString = Str.create().a(mDouble, decimals).getString();
        return new Unit(mStateBuilder, mKey, valAsString, mDouble);
    }

}
