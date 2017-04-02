package io.schinzel.basicutils.state;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "m")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class Property {
    @Getter(AccessLevel.PACKAGE) private String mKey;
    @Getter(AccessLevel.PACKAGE) private String mValueAsString;
    @Getter(AccessLevel.PACKAGE) private Object mValueAsObject;
    @Getter(AccessLevel.PACKAGE) private String mUnit;


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
