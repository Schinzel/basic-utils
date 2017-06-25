package io.schinzel.basicutils.beta.configvar;

import io.schinzel.basicutils.Checker;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Properties;

/**
 * The purpose of this class is to return Heroku config variables.
 * <p>
 * Created by schinzel on 2017-06-25.
 */
public class HerokuConfigVar {
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PACKAGE)
    private Properties dotEnvFile = PropertiesFile.getProperties(".env");
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PACKAGE)
    private Map<String, String> environmentVariables = System.getenv();


    HerokuConfigVar reset() {
        this.setDotEnvFile(PropertiesFile.getProperties(".env"));
        this.setEnvironmentVariables(System.getenv());
        return this;
    }


    /**
     * Loads a value for the argument key. First it tries to getProperties the value from
     * environment variables. If there was no such property was found, the property is looked
     * for in the .env file.
     *
     * @param keyName The name of the key
     * @return The value for the argument key.
     */
    String getValue(String keyName) {
        //If there was a environment variables
        String variable = Checker.isNotEmpty(System.getenv(keyName))
                //Set the variable from environment variables
                ? this.getEnvironmentVariables().get(keyName)
                //Set the variable from the .env file, i.e. running with foreman or in IDE
                : this.getDotEnvFile().getProperty(keyName);
        //If still no variable found
        if (Checker.isEmpty(variable)) {
            System.err.println("Environment variable for key '" + keyName + "' missing. "
                    + "If running locally using an IDE or foreman, the variable should be set in the .env file. "
                    + "If running on Heroku, the variable should be set with the Heroku web ui.");
            System.exit(2);
        }
        //If the variable is the empty-value placeholder, then return empty string else return variable.
        return (variable.equals("#EMPTY#")) ? "" : variable;
    }


}
