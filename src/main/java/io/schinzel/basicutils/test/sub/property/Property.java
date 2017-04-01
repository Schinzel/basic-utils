package io.schinzel.basicutils.test.sub.property;

import io.schinzel.basicutils.test.sub.StateBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor
public class Property {
    private String mKey;
    private String mValueAsString;
    private Object mValueAsObject;
    private String mUnit;



    /**
     *
     * @return The key of this property.
     */
    public String getKey() {
        return mKey;
    }


    /**
     *
     * @return The value held as a string.
     */
    public String getString() {
        return mKey + ":" + mValueAsString;
    }


    /**
     *
     * @return The value as an object.
     */
    public Object getObject() {
        return mValueAsObject;
    }

}
