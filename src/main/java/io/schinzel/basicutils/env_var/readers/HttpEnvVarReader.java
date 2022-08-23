package io.schinzel.basicutils.env_var.readers;

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
 * The purpose of this class is to read environment variables using a http
 * request.
 * <p>
 * This reader requires an endpoint that handles post requests. This endpoint
 * accepts the key of an environment variable as argument and returns the
 * value of the environment variable. The name of the variable is "key".
 */
@Builder
public class HttpEnvVarReader implements IEnvVarReader {
    @NonNull String url;
    @NonNull String username;
    @NonNull String password;
    @Builder.Default private boolean enableCache = false;
    // Set to final so that is not included in lombok builder
    final Cache<String, String> mCache = new Cache<>();


    @Override
    public String getValue(String key) {
        try {
            // Throw exception if argument keyName is null or empty
            Thrower.createInstance().throwIfVarEmpty(key, "key");
            // If cache is enabled and cache contains the argument key name
            if (enableCache && mCache.has(key)) {
                // Return the value for argument key name
                return mCache.get(key);
            }
            // Create data for basic authentication
            String base64login = getBase64Login(username, password);
            Map<String, String> data = Collections.singletonMap("key", key);
            // Create a connection
            Connection connection = getConnection(url, base64login, data);
            // Execute request
            String keyValue = getConnectionBody(connection);
            // If the cache is enabled
            if (enableCache) {
                // Add value to cache
                mCache.put(key, keyValue);
            }
            return keyValue;
        } catch (Exception e) {
            throw new RuntimeException("Error when getting value for key '"
                    + key + "' with url '" + url + "'." + e.getMessage());
        }
    }


    private static Connection getConnection(String url,
                                            String base64login,
                                            Map<String, String> data) {
        return Jsoup
                .connect(url)
                .method(Connection.Method.POST)
                .header("Authorization", "Basic " + base64login)
                .data(data)
                .timeout(500)
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
                        + " with body '" + response.body() + "'");
            }
            return response.body();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
