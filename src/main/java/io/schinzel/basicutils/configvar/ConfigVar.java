package io.schinzel.basicutils.configvar;

import io.schinzel.basicutils.Checker;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Map;

/**
 * The purpose of this class is to return config variables.
 * <p>
 * Created by schinzel on 2017-06-25.
 */
public class ConfigVar implements IConfigVar {
    /** A representation of the system variables. */
    @Getter(AccessLevel.PRIVATE) private final Map<String, String> environmentVariables;
    /** The file name of the properties file */
    @Getter(AccessLevel.PRIVATE) private final String propertiesFileName;
    /** A representation of the properties file. */
    @Getter(AccessLevel.PRIVATE) private final Map<String, String> propertiesFromFile;


    ConfigVar(String propertiesFileName) {
        this(propertiesFileName,
                PropertiesFile.getProperties(propertiesFileName),
                System.getenv());
    }


    ConfigVar(String propertiesFileName, Map<String, String> envVars, Map<String, String> propertiesFromFile) {
        this.environmentVariables = envVars;
        this.propertiesFileName = propertiesFileName;
        this.propertiesFromFile = propertiesFromFile;
    }


    /**
     * Returns the value for the argument key. First the property is looked for among environment
     * variables. If the property was not an environment variable, the property is looked for in
     * the properties file. If the property is not found in the properties file either, then a
     * RuntimeException is thrown.
     * <p>
     * If the placeholder #EMPTY# is found as a value for the argument key, empty string is
     * returned.
     *
     * @param keyName The name of the key
     * @return The value for the argument key.
     */
    public String getValue(String keyName) {
        //If there was a environment variables with the argument key
        String value = this.getEnvironmentVariables().containsKey(keyName)
                //Set the environment variable value
                ? this.getEnvironmentVariables().get(keyName)
                //Else, set the property file value
                : this.getPropertiesFromFile().get(keyName);
        //If still no property was found
        if (Checker.isEmpty(value)) {
            throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. "
                    + "No property with this key in either the environment variables nor in the properties file '" + this.getPropertiesFileName() + "'.");
        }
        //If the variable is the empty-value placeholder, then return empty string else return value.
        return (value.equals("#EMPTY#")) ? "" : value;
    }


}
