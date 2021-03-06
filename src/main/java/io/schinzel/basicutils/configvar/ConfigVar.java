package io.schinzel.basicutils.configvar;

import io.schinzel.basicutils.Checker;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.Map;

/**
 * The purpose of this class is to return config variables.
 * <p>
 * The class first tries to find the requested configuration variable among the system environment
 * variable and then the constructor argument file.
 * file.
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
    /** String to use in properties file or in environment variable if empty string should be returned as value. */
    static final String EMPTY_VALUE_PLACEHOLDER = "#EMPTY#";

    ConfigVar(String propertiesFileName) {
        this(propertiesFileName,
                System.getenv(),
                PropertiesFile.getProperties(propertiesFileName));
    }


    ConfigVar(String propertiesFileName, Map<String, String> envVars, Map<String, String> propertiesFromFile) {
        this.propertiesFileName = propertiesFileName;
        this.environmentVariables = envVars;
        this.propertiesFromFile = propertiesFromFile;
    }


    /**
     * @param propertiesFileName The name of the properties file. E.g. ".env"
     * @return A new instance.
     */
    public static ConfigVar create(String propertiesFileName) {
        return new ConfigVar(propertiesFileName);
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
        //If no property was found
        if (Checker.isEmpty(value)) {
            throw new RuntimeException("Configuration variable for key '" + keyName + "' missing. "
                    + "No property with this key in either the environment variables nor in the properties file '" + this.getPropertiesFileName() + "'.");
        }
        //If the value is the empty-value placeholder, then return empty string else return value.
        return (value.equals(EMPTY_VALUE_PLACEHOLDER)) ? "" : value;
    }


}
