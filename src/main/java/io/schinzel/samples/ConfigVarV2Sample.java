package io.schinzel.samples;

import io.schinzel.basicutils.configvar2.ConfigVarV2;
import io.schinzel.basicutils.configvar2.readers.EnvironmentConfigVarReader;
import io.schinzel.basicutils.configvar2.readers.PropertyFileConfigVarReader;

/**
 * The purpose of this class is to provide sample usage of ConfigVar2
 */
public class ConfigVarV2Sample {

    public static void main(String[] args) {
        final ConfigVarV2 configVarV2 = ConfigVarV2.builder()
                // Add a property file reader
                .configVarReader(new PropertyFileConfigVarReader("src/main/resources/io/schinzel/samples/sample_properties.txt"))
                // Add a reader that reads from environment variables
                .configVarReader(new EnvironmentConfigVarReader())
                .build();
        // The config var will go through its readers and the first reader to
        // contain the argument key, will return its value
        configVarV2.getValueAsStr("ape").writeToSystemOut();
        configVarV2.getValueAsStr("JAVA_HOME").writeToSystemOut();
    }
}