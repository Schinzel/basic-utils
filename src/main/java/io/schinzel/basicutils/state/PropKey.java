package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropKey {
    private StateBuilder mStateBuilder;


    public PropVal key(String key) {
        return new PropVal(mStateBuilder, key);
    }
}
