package io.schinzel.basicutils.configvar2.readers;

import java.util.Map;

/**
 * The purpose of this class is to read config variables from environment
 * variables.
 */
public class EnvironmentConfigVarReader implements IConfigVarReader {
    /** A representation of the system variables. */
    private final Map<String, String> environmentVariables;

    public EnvironmentConfigVarReader() {
        this(System.getenv());
    }


    /**
     * Exists for testing
     *
     * @param environmentVariables A collection of environment variables
     */
    EnvironmentConfigVarReader(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }


    @Override
    public String getValue(String keyName) {
        return environmentVariables.get(keyName);
    }
}