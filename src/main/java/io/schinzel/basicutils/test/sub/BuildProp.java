package io.schinzel.basicutils.test.sub;

public class BuildProp {
    private StateBuilder mStateBuilder;


    BuildProp(StateBuilder prop) {
        mStateBuilder = prop;
    }

    public StateBuilder buildProp(){
        return mStateBuilder;
    }

}
