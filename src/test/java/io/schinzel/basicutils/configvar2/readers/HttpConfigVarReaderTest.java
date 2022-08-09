package io.schinzel.basicutils.configvar2.readers;

import io.javalin.Javalin;
import io.javalin.core.security.AccessManager;
import io.schinzel.basicutils.FunnyChars;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.matchers.Null;
import java.util.Collections;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpConfigVarReaderTest {
    private Javalin mApp;
    private static String APP_USERNAME;
    private static String APP_PASSWORD;


    // Access manager is used by Javalin webserver to authenticate
    public static final AccessManager accessManager = (handler, ctx, permittedRoles) -> {
        String username = ctx.basicAuthCredentials().getUsername();
        String password = ctx.basicAuthCredentials().getPassword();
        if (APP_USERNAME.equals(username) && APP_PASSWORD.equals(password)) {
            handler.handle(ctx);
        } else {
            ctx.status(401).result("Unauthorized");
        }
    };


    /**
     * Starts a web server
     *
     * @param configVars The config vars
     */
    private void startWebServer(Map<String, String> configVars) {
        mApp = Javalin.create(config -> config.accessManager(accessManager))
                .get("/getConfigVar", ctx -> {
                    String keyName = ctx.queryParam("key_name");
                    String keyValue = configVars.get(keyName);
                    ctx.result(keyValue);
                })
                .start(7070);
    }


    @Before
    public void before() {
        APP_USERNAME = "my_username";
        APP_PASSWORD = "my_password";
    }

    @After
    public void after() {
        if (mApp != null) {
            mApp.stop();
        }
    }

    //------------------------------------------------------------------------
    // Builder
    //------------------------------------------------------------------------

    @Test
    public void builder_baseUrlMissing(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                HttpConfigVarReader.builder()
                        .username("my_username")
                        .password("my_password")
                        .variableName("key_name")
                        .build()
        );
    }

    @Test
    public void builder_usernameMissing(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                HttpConfigVarReader.builder()
                        .baseUrl("http://127.0.0.1:7070/getConfigVar")
                        .password("my_password")
                        .variableName("key_name")
                        .build()
        );
    }


    @Test
    public void builder_passwordMissing(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                HttpConfigVarReader.builder()
                        .baseUrl("http://127.0.0.1:7070/getConfigVar")
                        .username("my_username")
                        .variableName("key_name")
                        .build()
        );
    }


    @Test
    public void builder_keyNameMissing(){
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() ->
                HttpConfigVarReader.builder()
                        .baseUrl("http://127.0.0.1:7070/getConfigVar")
                        .username("my_username")
                        .password("my_password")
                        .build()
        );
    }

    //------------------------------------------------------------------------
    // getValue
    //------------------------------------------------------------------------
    @Test
    public void getValue_normalCase() {
        Map<String, String> configVars = Collections
                .singletonMap("key_name_1", "key_value_1");
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


    @Test
    public void getValue_argumentNull() {
        Map<String, String> configVars = Collections
                .singletonMap("key_name_1", "key_value_1");
        startWebServer(configVars);
        HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                reader.getValue(null)
        );
    }

    @Test
    public void getValue_argumentEmptyString() {
        Map<String, String> configVars = Collections
                .singletonMap("key_name_1", "key_value_1");
        startWebServer(configVars);
        HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                reader.getValue("")
        );
    }


    @Test
    public void getValue_polishCharsInKeyAndValue() {
        Map<String, String> configVars = Collections
                .singletonMap("źż", FunnyChars.POLISH_LETTERS.getString());
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build()
                .getValue("źż");
        assertThat(value).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
    }

    @Test
    public void getValue_oneCharKeyAndValue() {
        Map<String, String> configVars = Collections
                .singletonMap("a", "b");
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build()
                .getValue("a");
        assertThat(value).isEqualTo("b");
    }

    @Test
    public void getValue_longKeyAndValue() {
        String longString = FunnyChars.LONG_STRING.getString();
        Map<String, String> configVars = Collections
                .singletonMap(longString, longString);
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build()
                .getValue(longString);
        assertThat(value).isEqualTo(longString);
    }

    @Test
    public void getValue_usernameAndPasswordWithNonEnglishAlphabetChars() {
        String username = "źż#_!!//_";
        String password = "АВГДЄЅЗИѲ_;:/()*";
        APP_USERNAME = username;
        APP_PASSWORD = password;
        Map<String, String> configVars = Collections
                .singletonMap("my_key", "my_value");
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username(username)
                .password(password)
                .variableName("key_name")
                .build()
                .getValue("my_key");
        assertThat(value).isEqualTo("my_value");
    }


    @Test
    public void getValue_longUsernameAndPassword() {
        String username = FunnyChars.LONG_STRING.getString();
        String password = FunnyChars.LONG_STRING.getString();
        APP_USERNAME = username;
        APP_PASSWORD = password;
        Map<String, String> configVars = Collections
                .singletonMap("my_key", "my_value");
        startWebServer(configVars);
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username(username)
                .password(password)
                .variableName("key_name")
                .build()
                .getValue("my_key");
        assertThat(value).isEqualTo("my_value");
    }
}

