package io.schinzel.basicutils.configvar2;

import io.schinzel.basicutils.thrower.Thrower;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * The purpose of this file is to read the key-value properties from a file
 *
 * Handles key and values up to the size of at least 500 chars
 * Key and values can be UTF-8 chars.
 * Supports empty lines in files.
 * Comment lines start with #.
 */
public class PropertyFileReader implements IConfigVarReader {
    /** A representation of the properties file. */
    final Map<String, String> properties;

    public PropertyFileReader(String fileName) {
        try {
            Thrower.throwIfVarEmpty(fileName, "fileName");
            Thrower.throwIfTrue(!new File(fileName).exists(), "No file named '" + fileName + "' exists");
            //Read argument properties file from file system
            properties = getProperties(fileName);
        } catch (Exception e) {
            String errorMessage = "Error creating " + PropertyFileReader.class.getSimpleName() + ". " + e.getMessage();
            throw new RuntimeException(errorMessage);
        }
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
    private static Properties readPropertiesFile(String fileName) {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(fileName)), StandardCharsets.UTF_8))
        ) {
            Properties propsFromFile = new Properties();
            propsFromFile.load(reader);
            return propsFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Problems reading properties file '" + fileName + "'. " + e.getMessage());
        }
    }
}
