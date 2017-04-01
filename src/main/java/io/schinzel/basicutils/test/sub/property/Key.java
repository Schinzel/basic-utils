package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class Key {
    StateBuilder mStateBuilder;



    public Val key(String key) {
        return new Val(mStateBuilder, key);
    }
}
