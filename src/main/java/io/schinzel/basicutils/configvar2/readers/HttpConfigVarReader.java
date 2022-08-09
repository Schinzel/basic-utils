package io.schinzel.basicutils.configvar2.readers;

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
    @NonNull String variableName;


    @Override
    public String getValue(String keyName) {
        Thrower.createInstance()
                .throwIfVarEmpty(keyName, "keyName");
        String login = username + ":" + password;
        String base64login = Base64.getEncoder()
                .encodeToString(login.getBytes());
        Map<String, String> data = Collections
                .singletonMap("Authorization", "Basic " + base64login);
        String url = baseUrl + "?" + variableName + "=" + keyName;
        final Connection connection = Jsoup
                .connect(url)
                .method(Connection.Method.GET)
                .header("Authorization", "Basic " + base64login)
                .data(data)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false);
        try {
            final Connection.Response response = connection.execute();
            final String responseBody = response.body();
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


