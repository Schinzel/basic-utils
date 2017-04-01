package io.schinzel.basicutils.test.sub;

import java.util.ArrayList;
import java.util.List;

public class StateBuilder {
    List<Property> mProperties = new ArrayList<>();


    public static StateBuilder create() {
        return new StateBuilder();
    }


    public PropKey addProp() {
        return new PropKey(this);
    }


    public StateBuilder addProperty(Property property) {
        mProperties.add(property);
        return this;
    }


    public State buildState() {
        return new State(mProperties);
    }
}
