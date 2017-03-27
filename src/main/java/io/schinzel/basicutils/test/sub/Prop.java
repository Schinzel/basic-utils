package io.schinzel.basicutils.test.sub;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
public class Prop {
    @Getter private StateBuilder mStateBuilder;
    @Setter private String mKey;
    @Setter private String mValueAsString;
    @Setter private Object mValueAsObject;
    @Setter private String mUnit;


    Prop(StateBuilder stateBuilder) {
        mStateBuilder = stateBuilder;
    }

}
