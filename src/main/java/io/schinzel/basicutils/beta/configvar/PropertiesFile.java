package io.schinzel.basicutils.beta.configvar;

import io.schinzel.basicutils.Thrower;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * The purpose of this class is to load a properties file from the file system.
 * <p>
 * Created by schinzel on 2017-06-25.
 */
class PropertiesFile {


    /**
     * Returns the argument files as a properties object. The file location is relative the run
     * directory. If no such file, an empty properties is returned.
     *
     * @param fileName
     * @return
     */
    static Map<String, String> getProperties(String fileName) {
        Thrower.throwIfVarEmpty(fileName, "filenNme");
        if (!new File(fileName).exists()) {
            return Collections.emptyMap();
        }
        //Read argument properties file from file system
        Properties propsFromFile = PropertiesFile.readPropertiesFile(fileName);
        //Convert the properties to a map and return
        return propsFromFile.stringPropertyNames().stream()
                .collect(Collectors.toMap(p -> p, p -> propsFromFile.getProperty(p)));
    }


    /**
     * @param fileName The filename of the properties file.
     * @return Properties object created from the argument filename.
     */
    static Properties readPropertiesFile(String fileName) {
        BufferedReader is = null;
        try {
            is = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
            Properties propsFromFile = new Properties();
            propsFromFile.load(is);
            return propsFromFile;
        } catch (IOException e) {
            throw new RuntimeException("Problems reading properties file '" + fileName + "'. " + e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    //Ignore
                }
            }
        }
    }

}
