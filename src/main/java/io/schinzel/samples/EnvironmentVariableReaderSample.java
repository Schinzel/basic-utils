package io.schinzel.samples;

import io.schinzel.basicutils.env_var_reader.MultiEnvironmentVariableReader;
import io.schinzel.basicutils.env_var_reader.PropertyFileEnvironmentVariableReader;
import io.schinzel.basicutils.env_var_reader.SystemEnvironmentVariableReader;

/**
 * The purpose of this class is to provide sample usages of environment
 * variable readers
 */
public class EnvironmentVariableReaderSample {

    public static void main(String[] args) {
        final MultiEnvironmentVariableReader multiEnvironmentVariableReader = MultiEnvironmentVariableReader.builder()
                // Add a property file reader
                .varReader(new PropertyFileEnvironmentVariableReader("src/main/resources/io/schinzel/samples/sample_properties.txt"))
                // Add a reader that reads from environment variables
                .varReader(new SystemEnvironmentVariableReader())
                .build();
        // The environment variables instance will go through its readers and the
        // first reader to contain the argument key, will return its value
        multiEnvironmentVariableReader.getValueAsStr("ape").writeToSystemOut();
        multiEnvironmentVariableReader.getValueAsStr("JAVA_HOME").writeToSystemOut();
    }
}