package io.schinzel.basicutils.configvar2;

/**
 * The purpose of this interface is to read configuration variables.
 * <p>
 * An example of a configuration reader would be a properties file reader.
 */
public interface IConfigVarReader {

    /**
     * @param keyName The name of the key of key-value pair
     * @return True if there is key-value pair with the argument key
     */
    boolean containsKey(String keyName);

    /**
     * @param keyName The name of the key of key-value pair
     * @return The value of the key-value pair with the argument key
     */
    String getValue(String keyName);
}
