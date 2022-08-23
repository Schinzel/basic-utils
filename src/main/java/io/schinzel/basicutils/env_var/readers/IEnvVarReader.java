package io.schinzel.basicutils.env_var.readers;

/**
 * The purpose of this interface is to read environment variables.
 */
public interface IEnvVarReader {

    /**
     * @param key The key of the environment variable
     * @return The value of the environment variable with the argument key
     */
    String getValue(String key);
}
