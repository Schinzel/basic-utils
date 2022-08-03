package io.schinzel.basicutils.configvar2;

import io.schinzel.basicutils.collections.Cache;
import io.schinzel.basicutils.configvar.IConfigVar;
import io.schinzel.basicutils.configvar.IName;
import lombok.Builder;
import lombok.Singular;
import lombok.val;
import java.util.HashMap;
import java.util.List;

/**
 * The purpose of this class is to read configuration variables.
 *
 * ConfigVar version 1 was hard coded to first check for config variables among
 * environment variables and if not there in a properties file.
 *
 * This class takes an arbitrary number of config variable readers and
 * checks them in added order for the value of an argument key-name
 *
 */
@Builder
public class ConfigVarV2 implements IConfigVar {
    @Singular
    private List<IConfigVarReader> configVarReaders;
    private Boolean enableCache;
    // Set to final so that is not included in lombok builder
    final private Cache<String, String> mCache = new Cache<>();
    /** String to use in properties file or in environment variable if empty
     * string should be returned as value. */
    private static final String EMPTY_VALUE_PLACEHOLDER = "#EMPTY#";


    /**
     * @param objectWithName An object with a name
     * @return Returns the value of the property with the argument key.
     */
    public String getValue(IName objectWithName) {
        return this.getValue(objectWithName.getMyName());
    }


    /**
     * This method tries to find the argument key in the configuration
     * variable readers in the order the readers where added.
     *
     * @param keyName The name of the key of the configuration variable
     * @return The value of the configuration variable. An empty string if
     * the empty-value-placeholder "#EMPTY#" was encountered.
     * @throws RuntimeException If the argument key is not encountered in any
     *                          of the readers.
     */
    public String getValue(String keyName) {
        // If cache is enabled and cache contains the argument key name
        if (enableCache && mCache.has(keyName)){
            // Return the value for argument key name
            return mCache.get(keyName);
        }
        // Go through all readers
        for (IConfigVarReader reader : configVarReaders) {
            // Get the value of the argument key
            String value = reader.getValue(keyName);
            // If there was such a key-value pair with the argument key-name
            if (value != null) {
                // If the value was the empty-value-placeholder
                val returnValue = value.equals(EMPTY_VALUE_PLACEHOLDER)
                        ? "" // Set return to be empty string
                        : value; // Set return to be the actual value
                // If the cache is enabled
                if (enableCache){
                    // Add return value to cache
                    mCache.put(keyName, returnValue);
                }
                return returnValue;
            }
        }
        // If got here there was no key-value pair with argument key
        throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. ");
    }
}
