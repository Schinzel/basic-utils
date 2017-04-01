package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public class Property {
    @Getter private StateBuilder mStateBuilder;
    @Setter private String mKey;
    @Setter private String mValueAsString;
    @Setter private Object mValueAsObject;
    @Setter private String mUnit;


    Property(StateBuilder stateBuilder) {
        mStateBuilder = stateBuilder;
    }

}
