package io.schinzel.basicutils.configvar2.readers;

import io.schinzel.basicutils.collections.Cache;
import io.schinzel.basicutils.thrower.Thrower;
import lombok.Builder;
import lombok.NonNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

/**
 * The purpose of this class is to read config variables using a http
 * request
 */
@Builder
public class HttpConfigVarReader implements IConfigVarReader {
    @NonNull String baseUrl;
    @NonNull String username;
    @NonNull String password;
    @Builder.Default String variableName = "keyName";
    @Builder.Default private boolean enableCache = false;
    // Set to final so that is not included in lombok builder
    final Cache<String, String> mCache = new Cache<>();


    @Override
    public String getValue(String keyName) {
        try {
            Thrower.createInstance().throwIfVarEmpty(keyName, "keyName");
            // If cache is enabled and cache contains the argument key name
            if (enableCache && mCache.has(keyName)) {
                // Return the value for argument key name
                return mCache.get(keyName);
            }
            String url = baseUrl + "?" + variableName + "=" + keyName;
            String base64login = getBase64Login(username, password);
            Connection connection = getConnection(url, base64login);
            String keyValue = getConnectionBody(connection);
            // If the cache is enabled
            if (enableCache) {
                // Add return key value to cache
                mCache.put(keyName, keyValue);
            }
            return keyValue;
        } catch (Exception e) {
            throw new RuntimeException("Error when getting value for key '"
                    + keyName + "'. " + e.getMessage());
        }
    }


    private static Connection getConnection(String url, String base64login) {
        return Jsoup
                .connect(url)
                .method(Connection.Method.GET)
                .header("Authorization", "Basic " + base64login)
                //.data(data)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false);
    }


    private static String getBase64Login(String username, String password) {
        String login = username + ":" + password;
        return Base64.getEncoder().encodeToString(login.getBytes());
    }


    private static String getConnectionBody(Connection connection) {
        try {
            Connection.Response response = connection.execute();
            if (response.statusCode() != 200) {
                throw new RuntimeException("Got status code " + response.statusCode()
                        + " with body " + response.body());
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}


