package io.schinzel.basicutils.test.sub;

import io.schinzel.basicutils.test.sub.property.Key;

public class StateBuilder {

    public static StateBuilder create() {
        return new StateBuilder();
    }


    public Key addProp() {
        return new Key(this);
    }

    public State buildState(){
        return new State();
    }
}
