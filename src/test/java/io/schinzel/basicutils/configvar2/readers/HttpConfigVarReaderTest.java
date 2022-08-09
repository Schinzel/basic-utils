package io.schinzel.basicutils.configvar2.readers;

import com.google.common.collect.ImmutableMap;
import io.javalin.Javalin;
import io.javalin.core.security.AccessManager;
import org.junit.Test;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpConfigVarReaderTest {

    // Access manager is used by Javalin webserver
    public static final AccessManager accessManager = (handler, ctx, permittedRoles) -> {
        String username = ctx.basicAuthCredentials().getUsername();
        String password = ctx.basicAuthCredentials().getPassword();
        if ("my_username".equals(username) && "my_password".equals(password)) {
            handler.handle(ctx);
        } else {
            ctx.status(401).result("Unauthorized");
        }
    };


    /**
     * Starts a web server
     * @param configVars The config vars
     */
    private void startWebServer(Map<String, String> configVars) {
        Javalin.create(config -> config.accessManager(accessManager))
                .get("/getConfigVar", ctx -> {
                    String keyName = ctx.queryParam("key_name");
                    String keyValue = configVars.get(keyName);
                    ctx.result(keyValue);
                })
                .start(7070);
    }


    @Test
    public void apa() {
        Map<String, String> configVars = ImmutableMap.<String, String>builder()
                .put("key_name_1", "key_value_1")
                .build();
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build()
                .getValue("key_name_1");
        assertThat(value).isEqualTo("key_value_1");
    }
}

