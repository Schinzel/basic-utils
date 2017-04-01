package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class PropertyBuilder {
    StateBuilder mStateBuilder;
    String mKey;
    String mValAsString;
    Object mValAsObject;
    String mUnit;




    public StateBuilder buildProp() {
        new Property(mKey, mValAsString, mValAsObject, mUnit);
        return mStateBuilder;
    }

}
