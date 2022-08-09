package io.schinzel.basicutils.configvar2.readers;

import io.javalin.Javalin;
import io.javalin.core.security.AccessManager;
import io.schinzel.basicutils.FunnyChars;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpConfigVarReader_getValue_Test {
    private Javalin mApp;
    private static String APP_USERNAME;
    private static String APP_PASSWORD;
    private Map<String, String> configVars;

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
     */
    private void startWebServer() {
        mApp = Javalin.create(config -> config.accessManager(accessManager))
                .get("/getConfigVar", ctx -> {
                    String keyName = ctx.queryParam("key_name");
                    String keyValue = configVars.get(keyName);
                    if (keyValue != null) {
                        ctx.result(keyValue);
                    } else {
                        ctx.status(400)
                                .result("No key-value pair with key '" + keyName + "' exists.");
                    }
                })
                .start(7070);
    }


    @Before
    public void before() {
        APP_USERNAME = "my_username";
        APP_PASSWORD = "my_password";
        startWebServer();
    }

    @After
    public void after() {
        if (mApp != null) {
            mApp.stop();
        }
    }

    @Test
    public void getValue_normalCase() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
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
    public void getValue_cachedDisabledValueChanges_returnedUpdatedKeyValue() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
        HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .enableCache(false)
                .build();
        String value1 = reader.getValue("key_name_1");
        configVars = Collections.singletonMap("key_name_1", "key_value_2");
        String value2 = reader.getValue("key_name_1");
        assertThat(value1).isEqualTo("key_value_1");
        assertThat(value2).isEqualTo("key_value_2");
    }

    @Test
    public void getValue_cachedEnabledValueChanges_cachedKeyValue() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
        HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .enableCache(true)
                .build();
        String value1 = reader.getValue("key_name_1");
        configVars = Collections.singletonMap("key_name_1", "key_value_2");
        String value2 = reader.getValue("key_name_1");
        assertThat(value1).isEqualTo("key_value_1");
        assertThat(value2).isEqualTo("key_value_1");
    }

    @Test
    public void getValue_polishCharsInKeyAndValue() {
        configVars = Collections.singletonMap("źż", FunnyChars.POLISH_LETTERS.getString());
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
        configVars = Collections.singletonMap("a", "b");
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
        configVars = Collections.singletonMap(longString, longString);
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
        configVars = Collections.singletonMap("my_key", "my_value");
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
        configVars = Collections.singletonMap("my_key", "my_value");
        String value = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username(username)
                .password(password)
                .variableName("key_name")
                .build()
                .getValue("my_key");
        assertThat(value).isEqualTo("my_value");
    }


    //------------------------------------------------------------------------
    // Exceptions
    //------------------------------------------------------------------------


    @Test
    public void getValue_noEntryForKey_exception() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
        final HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://192.0.2.0:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("no_such_key")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            reader.getValue("key_name_1");
        });
    }

    @Test
    public void getValue_incorrectUrl_exception() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
        final HttpConfigVarReader reader = HttpConfigVarReader.builder()
                .baseUrl("http://192.0.2.0:7070/getConfigVar")
                .username("my_username")
                .password("my_password")
                .variableName("key_name")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
                    reader.getValue("key_name_1");
                });
    }

    @Test
    public void getValue_argumentNull() {
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
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
        configVars = Collections.singletonMap("key_name_1", "key_value_1");
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
}
