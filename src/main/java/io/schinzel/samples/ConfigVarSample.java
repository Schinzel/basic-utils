package io.schinzel.samples;

import io.schinzel.basicutils.configvar.ConfigVar;

/**
 * The purpose of this class is to provide sample usage of the ConfigVar class.
 * <p>
 * Created by Schinzel on 2017-02-27.
 */
public class ConfigVarSample {
    public static void main(String[] args) {
        ConfigVar configVar = ConfigVar.create("src/main/resources/io/schinzel/samples/sample_properties.txt");
        System.out.println("Property from file: '" + configVar.getValue("ape") + "'");
        System.out.println("Property from environment vars: '" + configVar.getValue("JAVA_HOME") + "'");
    }
}
