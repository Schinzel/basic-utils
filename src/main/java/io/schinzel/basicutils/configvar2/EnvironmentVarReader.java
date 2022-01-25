package io.schinzel.basicutils.configvar2;

import lombok.AccessLevel;
import lombok.Getter;
import java.util.Map;

/**
 * The purpose of this class is to read environment variables.
 */
public class EnvironmentVarReader implements IConfigVarReader {
    /** A representation of the system variables. */
    @Getter(AccessLevel.PRIVATE) private final Map<String, String> environmentVariables;

    EnvironmentVarReader() {
        environmentVariables = System.getenv();
    }

    @Override
    public boolean containsKey(String keyName) {
        return getEnvironmentVariables().containsKey(keyName);
    }

    @Override
    public String getValue(String keyName) {
        return getEnvironmentVariables().get(keyName);
    }
}
