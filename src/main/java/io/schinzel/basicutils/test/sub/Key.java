package io.schinzel.basicutils.test.sub;

public class Key {
    private String mKey;
    private StateBuilder mStateBuilder;


    Key(StateBuilder prop) {
        mStateBuilder = prop;
    }


    public Val key(String key) {
        mKey = key;
        return new Val(mStateBuilder);
    }
}
