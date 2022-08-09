package io.schinzel.basicutils.configvar2;

import io.schinzel.basicutils.configvar.IConfigVar;
import io.schinzel.basicutils.configvar2.readers.IConfigVarReader;
import lombok.Builder;
import lombok.Singular;
import java.util.List;

/**
 * The purpose of this class is to read configuration variables.
 * <p>
 * ConfigVar version 1 was hard coded to first check for config variables among
 * environment variables and if not there in a properties file.
 * <p>
 * This class takes an arbitrary number of config variable readers and
 * checks them in added order for the value of an argument key-name
 */
@Builder
public class ConfigVarV2 implements IConfigVar {
    @Singular
    private List<IConfigVarReader> configVarReaders;
    /**
     * String to use in properties file or in environment variable if empty
     * string should be returned as value.
     */
    private static final String EMPTY_VALUE_PLACEHOLDER = "#EMPTY#";


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
        // Go through all readers
        for (IConfigVarReader reader : configVarReaders) {
            // Get the value of the argument key
            String value = reader.getValue(keyName);
            // If there was such a key-value pair with the argument key-name
            if (value != null) {
                // If the value was the empty-value-placeholder
                return value.equals(EMPTY_VALUE_PLACEHOLDER)
                        ? "" // Set return to be empty string
                        : value; // Set return to be the actual value
            }
        }
        // If got here there was no key-value pair with argument key
        throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. ");
    }
}
