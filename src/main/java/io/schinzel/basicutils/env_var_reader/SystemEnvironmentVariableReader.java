package io.schinzel.basicutils.env_var_reader;

import java.util.Map;

/**
 * The purpose of this class is to read environment variables from the OS
 */
public class SystemEnvironmentVariableReader implements IEnvironmentVariableReader {
    /** A representation of the system variables. */
    private final Map<String, String> environmentVariables;

    public SystemEnvironmentVariableReader() {
        this(System.getenv());
    }


    /**
     * Exists for testing
     *
     * @param environmentVariables A collection of environment variables
     */
    SystemEnvironmentVariableReader(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }


    @Override
    public String getValue(String key) {
        return environmentVariables.get(key);
    }
}