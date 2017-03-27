package io.schinzel.basicutils.test.sub;

public class Key {
    private Prop mProp;


    Key(StateBuilder stateBuilder) {
        mProp = new Prop(stateBuilder);
    }


    public Val key(String key) {
        mProp.setKey(key);
        return new Val(mProp);
    }
}
