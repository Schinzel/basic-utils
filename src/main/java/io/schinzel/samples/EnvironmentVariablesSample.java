package io.schinzel.samples;

import io.schinzel.basicutils.env_var.EnvironmentVariables;
import io.schinzel.basicutils.env_var.readers.SystemEnvVarReader;
import io.schinzel.basicutils.env_var.readers.PropertyFileVarReader;

/**
 * The purpose of this class is to provide sample usage of EnvironmentVariables
 */
public class EnvironmentVariablesSample {

    public static void main(String[] args) {
        final EnvironmentVariables environmentVariables = EnvironmentVariables.builder()
                // Add a property file reader
                .varReader(new PropertyFileVarReader("src/main/resources/io/schinzel/samples/sample_properties.txt"))
                // Add a reader that reads from environment variables
                .varReader(new SystemEnvVarReader())
                .build();
        // The environment variables instance will go through its readers and the
        // first reader to contain the argument key, will return its value
        environmentVariables.getValueAsStr("ape").writeToSystemOut();
        environmentVariables.getValueAsStr("JAVA_HOME").writeToSystemOut();
    }
}