package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class Unit {
    StateBuilder mStateBuilder;
    String mKey;
    String mValAsString;
    Object mValAsObject;



    public PropertyBuilder unit(String unit) {
        return new PropertyBuilder(mStateBuilder, mKey, mValAsString, mValAsObject, unit);
    }


    public StateBuilder buildProp() {
        return mStateBuilder;
    }


}
