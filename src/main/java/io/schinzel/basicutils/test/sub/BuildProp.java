package io.schinzel.basicutils.test.sub;

public class BuildProp {
    private Prop mProp;


    BuildProp(Prop prop) {
        mProp = prop;
    }


    public StateBuilder buildProp() {
        return mProp.getStateBuilder();
    }

}
