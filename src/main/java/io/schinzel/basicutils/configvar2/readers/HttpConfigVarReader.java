package io.schinzel.basicutils.configvar2.readers;

import lombok.Builder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;


@Builder
public class HttpConfigVarReader implements IConfigVarReader {
    String baseUrl;
    String username;
    String password;
    String variableName;


    @Override
    public String getValue(String keyName) {
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


