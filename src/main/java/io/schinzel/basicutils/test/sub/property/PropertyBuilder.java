package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class PropertyBuilder {
    private StateBuilder mStateBuilder;
    private String mKey;
    private String mValAsString;
    private Object mValAsObject;
    private String mUnit;


    public StateBuilder buildProp() {
        new Property(mKey, mValAsString, mValAsObject, mUnit);
        return mStateBuilder;
    }

}
