package io.schinzel.basicutils.beta.configvar;

import com.google.common.base.Charsets;
import io.schinzel.basicutils.Thrower;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
     * @param filename
     * @return
     */
    static Map<String, String> getProperties(String filename) {
        Thrower.throwIfVarEmpty(filename, "filename");
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = Files.newBufferedReader(path, Charsets.UTF_8);
                Properties propsFromFile = new Properties();
                propsFromFile.load(bufferedReader);
                return propsFromFile.stringPropertyNames().stream()
                        .collect(Collectors.toMap(p -> p, p -> propsFromFile.getProperty(p)));
            } catch (IOException e) {
                throw new RuntimeException("Problems reading properties file '" + filename + "'. " + e.getMessage());
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        //Ignore
                    }
                }
            }
        }
        return Collections.emptyMap();
    }

}
