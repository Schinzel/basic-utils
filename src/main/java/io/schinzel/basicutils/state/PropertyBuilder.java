package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropertyBuilder {
    private StateBuilder mStateBuilder;
    private String mKey;
    private String mValAsString;
    private Object mValAsObject;
    private String mUnit;


    public StateBuilder buildProp() {
        Property prop = new Property(mKey, mValAsString, mValAsObject, mUnit);
        return mStateBuilder.addProperty(prop);
    }

}
