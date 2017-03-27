package io.schinzel.basicutils.test.sub;

public class Val {
    private Prop mProp;


    Val(Prop prop) {
        mProp = prop;
    }


    public Unit val(int val) {
        mProp.setValueAsString(String.valueOf(val));
        mProp.setValueAsObject(val);
        return new Unit(mProp);
    }


    public Unit val(String val) {
        mProp.setValueAsString(val);
        mProp.setValueAsObject(val);
        return new Unit(mProp);
    }


    public ADouble val(double val) {
        mProp.setValueAsObject(val);
        return new ADouble(mProp, val);
    }
}
