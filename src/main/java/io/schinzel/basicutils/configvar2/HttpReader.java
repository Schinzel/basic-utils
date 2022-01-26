package io.schinzel.basicutils.configvar2;

import lombok.Builder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@Builder
public class HttpReader implements IConfigVarReader {
    String url;
    String username;
    String password;


    @Override
    public boolean containsKey(String keyName) {
        return false;
    }

    @Override
    public String getValue(String keyName) {
        return null;
    }

    String getValueInt(String keyName) {
        String login = username + ":" + password;
        String base64login = Base64.getEncoder()
                .encodeToString(login.getBytes());
        Map<String, String> data = Collections.singletonMap("keyName", keyName);

        Jsoup
                .connect(url)
                .method(Connection.Method.GET)
                .header("Authorization", "Basic " + base64login)
                .data(data)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false);
        return null;
    }
}
