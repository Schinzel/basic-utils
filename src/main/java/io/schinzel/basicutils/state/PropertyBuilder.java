package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

/**
 * The purpose of this class is to build a property.
 */

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class PropertyBuilder {
    private StateBuilder mStateBuilder;
    private String mKey;
    private String mValAsString;
    private Object mValAsObject;
    private String mUnit;


    /**
     * Builds a property.
     *
     * @return The state builder.
     */
    public StateBuilder buildProp() {
        Property prop = new Property(mKey, mValAsString, mValAsObject, mUnit);
        //Add the created property to the state builder.
        return mStateBuilder.addProperty(prop);
    }

}
