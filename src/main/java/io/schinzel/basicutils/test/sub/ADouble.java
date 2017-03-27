package io.schinzel.basicutils.test.sub;

import io.schinzel.basicutils.str.Str;

public class ADouble {
    private Prop mProp;
    private double mDouble;


    ADouble(Prop prop, double val) {
        mProp = prop;
        mDouble = val;
    }


    public BuildProp decimals(int decimals) {
        mProp.setValueAsString(Str.create().a(mDouble, decimals).getString());
        return new BuildProp(mProp);
    }

}
