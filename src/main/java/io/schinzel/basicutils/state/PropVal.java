package io.schinzel.basicutils.state;

import io.schinzel.basicutils.Checker;
import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.str.Str;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;
import org.json.JSONArray;

import java.util.List;

/**
 * The purpose of this class is to add a value to a property.
 */

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropVal {
    /** The state builder that created the property builder. */
    private StateBuilder mStateBuilder;
    /** The key for which to add a value */
    private String mKey;


    /**
     * Add an integer value.
     *
     * @param val The integer to add.
     * @return  Property unit adder.
     */
    public PropUnit val(int val) {
        return this.val((long) val);
    }


    /**
     * Add a long value.
     *
     * @param val The long value to add.
     * @return  Property unit adder.
     */
    public PropUnit val(long val) {
        return this.getUnit(Str.create().a(val).toString(), val);
    }


    /**
     * Add a value.
     *
     * @param val The boolean value to add.
     * @return  Property unit adder.
     */
    public PropUnit val(boolean val) {
        return this.getUnit(String.valueOf(val), val);
    }


    /**
     * Add a value.
     *
     * @param val The string value to add.
     * @return  Property unit adder.
     */
    public PropUnit val(String val) {
        if (val == null) {
            val = "";
        }
        return this.getUnit(val, val);
    }


    /**
     * Add a value.
     *
     * @param val The float to add.
     * @return  Property unit adder.
     */
    public PropDouble val(float val) {
        return this.val((double) val);
    }


    /**
     * Add a value.
     *
     * @param val The value to add.
     * @return  Property unit adder.
     */
    public PropDouble val(double val) {
        return new PropDouble(mStateBuilder, mKey, val);
    }


    /**
     * Add a value.
     *
     * @param values The string array to add.
     * @return  Property unit adder.
     */
    public PropUnit val(String[] values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        String valAsString = "(" + String.join(", ", values) + ")";
        return this.getUnit(valAsString, new JSONArray(values));
    }


    /**
     * Add a value.
     *
     * @param values The list of strings to add.
     * @return  Property unit adder.
     */
    public PropUnit val(List<String> values) {
        if (Checker.isEmpty(values)) {
            return this.getUnit("", "");
        }
        return this.val(values.toArray(EmptyObjects.EMPTY_STRING_ARRAY));
    }


    /**
     *
     * @param valAsString
     * @param valAsObject
     * @return Property unit adder. 
     */
    private PropUnit getUnit(String valAsString, Object valAsObject) {
        return new PropUnit(mStateBuilder, mKey, valAsString, valAsObject);
    }


}
