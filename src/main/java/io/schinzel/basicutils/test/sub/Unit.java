package io.schinzel.basicutils.test.sub;

public class Unit {
    private Prop mProp;


    Unit(Prop prop) {
        mProp = prop;
    }


    public BuildProp unit(String unit) {
        return new BuildProp(mProp);
    }


    public StateBuilder buildProp() {
        return mProp.getStateBuilder();
    }


}
