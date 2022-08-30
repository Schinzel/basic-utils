package io.schinzel.basicutils.env_var;

import io.schinzel.basicutils.env_var.readers.IEnvVarReader;
import io.schinzel.basicutils.str.Str;
import lombok.Builder;
import lombok.Singular;
import java.util.List;

/**
 * The purpose of this class is to read environment variables.
 * <p>
 * ConfigVar - which was the first version of this class -  was hard coded
 * to first check for variables among environment variables and
 * if not there in a properties file.
 * <p>
 * This class takes an arbitrary number of variable readers and
 * checks them in added order for the value of an argument key-name
 */
@Builder
public class EnvironmentVariables implements IEnvironmentVariables {
    @Singular
    private List<IEnvVarReader> varReaders;
    /**
     * String to use in properties file or in environment variable if empty
     * string should be returned as value.
     */
    private static final String EMPTY_VALUE_PLACEHOLDER = "#EMPTY#";


    /**
     * This method tries to find the argument key among the environment
     * variable readers in the order the readers where added.
     *
     * @param keyName The key of the environment variable
     * @return The value of the environment variable. Returns an empty string if
     * the empty-value-placeholder "#EMPTY#" was encountered. Returns null
     * if no environment variable with argument key-name was found in any of
     * the environment variable readers.
     */
    public String getValue(String keyName) {
        // Go through all readers
        for (IEnvVarReader reader : varReaders) {
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
        // If got here there was no key-value pair with argument key in any of the readers
        return null;
    }


    /**
     * Same as getValue(String). The only difference is that the return is a
     * Str instead of a String.
     */
    public Str getValueAsStr(String keyName){
        return Str.create(this.getValue(keyName));
    }
}