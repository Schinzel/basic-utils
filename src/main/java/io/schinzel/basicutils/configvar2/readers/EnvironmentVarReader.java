package io.schinzel.basicutils.configvar2.readers;

import java.util.Map;

/**
 * The purpose of this class is to read environment variables.
 */
public class EnvironmentVarReader implements IConfigVarReader {
    /** A representation of the system variables. */
    private final Map<String, String> environmentVariables;

    public EnvironmentVarReader() {
        this(System.getenv());
    }


    /**
     * Exists for testing
     *
     * @param environmentVariables
     */
    EnvironmentVarReader(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }


    @Override
    public String getValue(String keyName) {
        return environmentVariables.get(keyName);
    }
}