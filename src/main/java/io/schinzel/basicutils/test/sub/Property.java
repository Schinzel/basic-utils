package io.schinzel.basicutils.test.sub;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Property {
    private String mKey;
    private String mValueAsString;
    private Object mValueAsObject;
    private String mUnit;


    /**
     * @return The key of this property.
     */
    String getKey() {
        return mKey;
    }


    /**
     * @return The value held as a string.
     */
    String getString() {
        return mKey + ":" + mValueAsString + mUnit;
    }


    /**
     * @return The value as an object.
     */
    Object getObject() {
        return mValueAsObject;
    }

}
