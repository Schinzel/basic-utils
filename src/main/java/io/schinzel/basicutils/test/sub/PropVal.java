package io.schinzel.basicutils.test.sub;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.EmptyObjects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import org.json.JSONArray;

import java.util.List;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropVal {
    private StateBuilder mStateBuilder;
    private String mKey;


    public PropUnit val(int val) {
        return this.val((long) val);
    }


    public PropUnit val(long val) {
        return this.getUnit(String.valueOf(val), val);
    }


    public PropUnit val(boolean val) {
        return this.getUnit(String.valueOf(val), val);
    }


    public PropUnit val(String val) {
        return this.getUnit(val, val);
    }


    public PropDouble val(float val) {
        return this.val((double) val);
    }


    public PropDouble val(double val) {
        return new PropDouble(mStateBuilder, mKey, val);
    }


    public PropUnit val(String[] values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        String valAsString = "(" + String.join(",", values) + ")";
        return this.getUnit(valAsString, new JSONArray(values));
    }


    public PropUnit val(List<String> values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        return this.val(values.toArray(EmptyObjects.EMPTY_STRING_ARRAY));
    }


    private PropUnit getUnit(String valAsString, Object valAsObject) {
        return new PropUnit(mStateBuilder, mKey, valAsString, valAsObject);
    }


}
