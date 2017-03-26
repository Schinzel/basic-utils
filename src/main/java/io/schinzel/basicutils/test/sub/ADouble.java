package io.schinzel.basicutils.test.sub;

public class ADouble {
    private StateBuilder mStateBuilder;
    private int mDecimals;


    ADouble(StateBuilder prop) {
        mStateBuilder = prop;
    }


    public BuildProp decimals(int decimals){
        mDecimals = decimals;
        return new BuildProp(mStateBuilder);
    }

}
