package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropUnit {
    private StateBuilder mStateBuilder;
    private String mKey;
    private String mValAsString;
    private Object mValAsObject;


    public PropertyBuilder unit(String unit) {
        return new PropertyBuilder(mStateBuilder, mKey, mValAsString, mValAsObject, unit);
    }


    public StateBuilder buildProp() {
        return this.unit("").buildProp();
    }


}
