package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to optionally add a unit to a property.
 */

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropUnit {
    private StateBuilder mStateBuilder;
    private String mKey;
    private String mValAsString;
    private Object mValAsObject;


    /**
     * Adds a unit to a property.
     *
     * @param unit The unit to add.
     * @return A builder for the property.
     */
    public PropertyBuilder unit(String unit) {
        return new PropertyBuilder(mStateBuilder, mKey, mValAsString, mValAsObject, unit);
    }


    /**
     * Builds the property.
     *
     * @return The state builder
     */
    public StateBuilder buildProp() {
        return this.unit("").buildProp();
    }


}
