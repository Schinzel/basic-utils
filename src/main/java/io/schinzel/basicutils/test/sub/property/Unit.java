package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;

public class Unit {
    private Property mProperty;


    Unit(Property property) {
        mProperty = property;
    }


    public PropertyBuilder unit(String unit) {
        return new PropertyBuilder(mProperty);
    }


    public StateBuilder buildProp() {
        return mProperty.getStateBuilder();
    }


}
