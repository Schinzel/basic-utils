package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.test.sub.StateBuilder;
import org.json.JSONArray;

import java.util.List;

public class Val {
    private StateBuilder mStateBuilder;
    private String mKey;


    Val(StateBuilder stateBuilder, String key) {
        mStateBuilder = stateBuilder;
        mKey = key;
    }


    public Unit val(int val) {
        return this.val((long) val);
    }


    public Unit val(long val) {
        return this.getUnit(String.valueOf(val), val);
    }


    public Unit val(boolean val) {
        return this.getUnit(String.valueOf(val), val);
    }


    public Unit val(String val) {
        return this.getUnit(val, val);
    }


    public ADouble val(float val) {
        return this.val((double) val);
    }


    public ADouble val(double val) {
        return new ADouble(mStateBuilder, mKey, val);
    }


    public Unit val(String[] values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        return this.getUnit(String.join(", ", values), new JSONArray(values));
    }


    public Unit val(List<String> values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        return this.getUnit(String.join(", ", values), new JSONArray(values));
    }


    private Unit getUnit(String valAsString, Object valAsObject) {
        return new Unit(mStateBuilder, mKey, valAsString, valAsObject);
    }


}
