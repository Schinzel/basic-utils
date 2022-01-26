package io.schinzel.basicutils.configvar2;

import lombok.Builder;
import lombok.Singular;
import java.util.List;
import java.util.Optional;

/**
 * The purpose of this class is to read configuration variables.
 */
@Builder
public class ConfigVar2 {
    @Singular
    private List<IConfigVarReader> configVarReaders;
    /** String to use in properties file or in environment variable if empty string should be returned as value. */
    static final String EMPTY_VALUE_PLACEHOLDER = "#EMPTY#";


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
        for (IConfigVarReader reader : configVarReaders) {
            if (reader.containsKey(keyName)) {
                String value = reader.getValue(keyName);
                return value.equals(EMPTY_VALUE_PLACEHOLDER)
                        ? ""
                        : value;
            }
        }
        throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. ");
    }


    public String getValue3(String keyName) {
        for (IConfigVarReader reader : configVarReaders) {
            String value;
            if ((value = reader.getValue(keyName)) != null) {
                return value.equals(EMPTY_VALUE_PLACEHOLDER)
                        ? ""
                        : value;
            }
        }
        throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. ");
    }


    public String getValue2(String keyName) {
        // If got here the requested key name was not found
        final Optional<IConfigVarReader> reader = configVarReaders.stream()
                .filter(e -> e.containsKey(keyName))
                .findFirst();
        if (reader.isPresent()) {
            //If the value is the empty-value placeholder, then return empty string else return value.
            return (reader.equals(EMPTY_VALUE_PLACEHOLDER))
                    ? ""
                    : reader.get().getValue(keyName);
        }
        throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. ");
    }

}
