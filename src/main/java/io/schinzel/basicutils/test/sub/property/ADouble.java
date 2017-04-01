package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.str.Str;
import io.schinzel.basicutils.test.sub.StateBuilder;

public class ADouble {
    StateBuilder mStateBuilder;
    String mKey;
    private double mDouble;


    ADouble(StateBuilder stateBuilder, String key, double val) {
        mStateBuilder = stateBuilder;
        mKey = key;
        mDouble = val;
    }


    public Unit decimals(int decimals) {
        String valAsString = Str.create().a(mDouble, decimals).getString();
        return new Unit(mStateBuilder, mKey, valAsString, mDouble);
    }

}
