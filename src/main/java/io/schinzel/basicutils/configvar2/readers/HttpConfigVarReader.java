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
        Thrower.createInstance()
                .throwIfVarEmpty(keyName, "keyName");
        // If cache is enabled and cache contains the argument key name
        if (enableCache && mCache.has(keyName)) {
            // Return the value for argument key name
            return mCache.get(keyName);
        }
        String login = username + ":" + password;
        String base64login = Base64.getEncoder()
                .encodeToString(login.getBytes());
        Map<String, String> data = Collections
                .singletonMap("Authorization", "Basic " + base64login);
        String url = baseUrl + "?" + variableName + "=" + keyName;
        Connection connection = Jsoup
                .connect(url)
                .method(Connection.Method.GET)
                .header("Authorization", "Basic " + base64login)
                .data(data)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false);
        try {
            Connection.Response response = connection.execute();
            String keyValue = response.body();
            // If the cache is enabled
            if (enableCache) {
                // Add return key value to cache
                mCache.put(keyName, keyValue);
            }
            return keyValue;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


