package io.schinzel.basicutils.configvar2.readers;

import com.google.common.collect.ImmutableMap;
import io.javalin.Javalin;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Map;
import static org.junit.Assert.*;

public class HttpConfigVarReaderTest {



    private void startWebServer(Map<String, String> configVars){
        Javalin app = Javalin.create().start(7070);
        app.get("/getConfigVar", ctx -> {
            String keyName = ctx.queryParam("key_name");
            System.out.println("Incoming key name was '" + keyName + "'");
            String keyValue = configVars.get(keyName);
            System.out.println("Found key value '" + keyValue + "'");
            ctx.result(keyValue);
        });
    }


    @Test
    public void apa() {
        Map<String, String> configVars = ImmutableMap.<String, String>builder()
                .put("key_name_1", "key_value_1")
                .build();
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .url("http://127.0.0.1:7070/getConfigVar?key_name=key_name_1")
                .build()
                .getValue("sddsfs");
        System.out.println(value);
    }
}