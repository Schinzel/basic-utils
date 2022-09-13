package io.schinzel.basicutils.env_var_reader;

import io.schinzel.basicutils.configvar.IName;

/**
 * The purpose of this interface is to read environment variables.
 */
public interface IEnvironmentVariableReader {

    /**
     * @param key The key of the environment variable
     * @return The value of the environment variable with the argument key
     */
    String getValue(String key);

    /**
     * @param key An object with a name
     * @return Returns the value of the environment variable with the argument key.
     */
    default String getValue(IName key) {
        return this.getValue(key.getMyName());
    }
}