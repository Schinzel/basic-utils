package io.schinzel.basicutils.test.sub;

public class Val {
    private StateBuilder mStateBuilder;


    Val(StateBuilder prop) {
        mStateBuilder = prop;
    }


    public Unit val(int val) {
        return new Unit(mStateBuilder);
    }


    public Unit val(String val) {
        return new Unit(mStateBuilder);
    }


    public ADouble val(double val) {
        return new ADouble(mStateBuilder);
    }
}
