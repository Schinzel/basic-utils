package io.schinzel.basicutils.test.sub;

public class Unit {
    private StateBuilder mStateBuilder;

    Unit(StateBuilder prop){
        mStateBuilder = prop;
    }

    public BuildProp unit(String unit){
        return new BuildProp(mStateBuilder);
    }

    public StateBuilder buildProp(){
        return mStateBuilder;
    }


}
