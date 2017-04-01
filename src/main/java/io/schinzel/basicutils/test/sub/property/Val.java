package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.Checker;

public class Val {
    private Property mProperty;


    Val(Property property) {
        mProperty = property;
    }


    public Unit val(int val) {
        return this.val((long) val);
    }


    public Unit val(long val) {
        mProperty.setValueAsString(String.valueOf(val));
        mProperty.setValueAsObject(val);
        return new Unit(mProperty);
    }


    public Unit val(boolean val) {
        mProperty.setValueAsString(String.valueOf(val));
        mProperty.setValueAsObject(val);
        return new Unit(mProperty);
    }


    public Unit val(String val) {
        mProperty.setValueAsString(val);
        mProperty.setValueAsObject(val);
        return new Unit(mProperty);
    }


    public ADouble val(float val) {
        return this.val((double) val);
    }


    public ADouble val(double val) {
        mProperty.setValueAsObject(val);
        return new ADouble(mProperty, val);
    }


    public Unit val(String[] values) {
        if (Checker.isEmpty(values)) {
            return this.val("");
        }
        return this.val(String.join(", ", values));
    }



}
