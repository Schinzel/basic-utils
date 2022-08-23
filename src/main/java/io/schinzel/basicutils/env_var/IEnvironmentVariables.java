package io.schinzel.basicutils.env_var;

import io.schinzel.basicutils.configvar.IName;

public interface IEnvironmentVariables {
    /**
     * @param keyName The name of the key
     * @return Returns the value of the environment variable with the argument key.
     */
    String getValue(String keyName);


    /**
     * @param objectWithName An object with a name
     * @return Returns the value of the environment variable with the argument key.
     */
    default String getValue(IName objectWithName) {
        return this.getValue(objectWithName.getMyName());
    }
}
