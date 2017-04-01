package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;

public class Key {
    private Property mProperty;


    public Key(StateBuilder stateBuilder) {
        mProperty = new Property(stateBuilder);
    }


    public Val key(String key) {
        mProperty.setKey(key);
        return new Val(mProperty);
    }
}
