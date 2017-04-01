package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;

public class PropertyBuilder {
    private Property mProperty;


    PropertyBuilder(Property property) {
        mProperty = property;
    }


    public StateBuilder buildProp() {
        return mProperty.getStateBuilder();
    }

}
