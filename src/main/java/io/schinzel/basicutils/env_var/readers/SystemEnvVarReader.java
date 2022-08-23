package io.schinzel.basicutils.env_var.readers;

import java.util.Map;

/**
 * The purpose of this class is to read environment variables from the OS
 */
public class SystemEnvVarReader implements IEnvVarReader {
    /** A representation of the system variables. */
    private final Map<String, String> environmentVariables;

    public SystemEnvVarReader() {
        this(System.getenv());
    }


    /**
     * Exists for testing
     *
     * @param environmentVariables A collection of environment variables
     */
    SystemEnvVarReader(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
    }


    @Override
    public String getValue(String key) {
        return environmentVariables.get(key);
    }
}