package io.schinzel.basicutils.test.sub;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropKey {
    StateBuilder mStateBuilder;



    public PropVal key(String key) {
        return new PropVal(mStateBuilder, key);
    }
}
