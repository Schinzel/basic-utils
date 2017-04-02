package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to add a key to a property.
 */

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropKey {
    private StateBuilder mStateBuilder;


    /**
     * Adds a key to a property.
     *
     * @param key The key to add.
     * @return Property value adder.
     */
    public PropVal key(String key) {
        return new PropVal(mStateBuilder, key);
    }
}
