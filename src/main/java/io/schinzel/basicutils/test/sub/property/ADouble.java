package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.str.Str;

public class ADouble {
    private Property mProperty;
    private double mDouble;


    ADouble(Property property, double val) {
        mProperty = property;
        mDouble = val;
    }


    public PropertyBuilder decimals(int decimals) {
        mProperty.setValueAsString(Str.create().a(mDouble, decimals).getString());
        return new PropertyBuilder(mProperty);
    }

}
