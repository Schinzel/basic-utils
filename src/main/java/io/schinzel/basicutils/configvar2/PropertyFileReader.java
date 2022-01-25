package io.schinzel.basicutils.configvar2;

import io.schinzel.basicutils.thrower.Thrower;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * The purpose of this file is to read the key-value properties from a file
 */
public class PropertyFileReader implements IConfigVarReader {
    /** A representation of the properties file. */
    private final Map<String, String> properties;

    PropertyFileReader(String fileName) {
        properties = new File(fileName).exists()
                ? getProperties(fileName)
                : Collections.emptyMap();
    }

    @Override
    public boolean containsKey(String keyName) {
        return properties.containsKey(keyName);
    }

    @Override
    public String getValue(String keyName) {
        return properties.get(keyName);
    }


    /**
     * Returns the argument file as a properties object. The file location is relative the run
     * directory. If no such file, an empty map is returned.
     *
     * @param fileName The name of the properties file.
     * @return The properties file as a map
     */
    static Map<String, String> getProperties(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "fileName");
        if (!new File(fileName).exists()) {
            return Collections.emptyMap();
        }
        //Read argument properties file from file system
        Properties propsFromFile = readPropertiesFile(fileName);
        //Convert the properties to a map and return
        return propsFromFile.stringPropertyNames().stream()
                .collect(Collectors.toMap(p -> p, propsFromFile::getProperty));
    }


    /**
     * @param fileName The filename of the properties file.
     * @return Properties object created from the argument filename.
     */
    static Properties readPropertiesFile(String fileName) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))
        ) {
            Properties propsFromFile = new Properties();
            propsFromFile.load(reader);
            return propsFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Problems reading properties file '" + fileName + "'. " + e.getMessage());
        }
    }
}
