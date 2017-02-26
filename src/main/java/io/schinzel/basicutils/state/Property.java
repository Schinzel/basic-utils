package io.schinzel.basicutils.state;

/**
 * The purpose of this class represent a key-value pair.
 *
 * @author schinzel
 */
class Property {

    /**
     * The key of the property.
     */
    final String mKey;
    /**
     * The value as a string.
     */
    final String mValueAsString;
    /**
     * The value as an object. The variable holds for example a Double for
     * double, a String for a String and so on.
     */
    final Object mValueAsObject;


    /**
     * Adds a key and its corresponding value as a string and as an object. The
     * object can be a Double, String and so on. The string and value should be
     * different representations of the same values. For example a decimal
     * number could be stored as the string "12.12" and the double 12.1211111.
     *
     * @param key The key.
     * @param valueAsString The value to store as a string.
     * @param valueAsObject The value to store as an object.
     */
    Property(String key, String valueAsString, Object valueAsObject) {
        mKey = key;
        mValueAsString = valueAsString;
        mValueAsObject = valueAsObject;
    }


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
