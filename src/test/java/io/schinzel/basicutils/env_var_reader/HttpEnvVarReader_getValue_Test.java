package io.schinzel.basicutils.env_var_reader;

import io.javalin.Javalin;
import io.javalin.security.AccessManager;
import io.schinzel.basicutils.FunnyChars;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Collections;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpEnvVarReader_getValue_Test {
    private Javalin mApp;
    private static String APP_USERNAME;
    private static String APP_PASSWORD;
    private Map<String, String> environmentVariables;

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
                .post("/getEnvVar", ctx -> {
                    String key = ctx.formParam("key");
                    String value = environmentVariables.get(key);
                    if (value != null) {
                        ctx.result(value);
                    } else {
                        ctx.status(400)
                                .result("No variable with key '" + key + "' exists.");
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
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build()
                .getValue("key_name_1");
        assertThat(value).isEqualTo("key_value_1");
    }

    @Test
    public void getValue_cachedDisabledValueChanges_returnedUpdatedKeyValue() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .enableCache(false)
                .build();
        String value1 = reader.getValue("key_name_1");
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_2");
        String value2 = reader.getValue("key_name_1");
        assertThat(value1).isEqualTo("key_value_1");
        assertThat(value2).isEqualTo("key_value_2");
    }

    @Test
    public void getValue_cachedEnabledValueChanges_cachedKeyValue() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .enableCache(true)
                .build();
        String value1 = reader.getValue("key_name_1");
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_2");
        String value2 = reader.getValue("key_name_1");
        assertThat(value1).isEqualTo("key_value_1");
        assertThat(value2).isEqualTo("key_value_1");
    }

    @Test
    public void getValue_polishCharsInKeyAndValue() {
        environmentVariables = Collections.singletonMap("źż", FunnyChars.POLISH_LETTERS.getString());
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build()
                .getValue("źż");
        assertThat(value).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
    }

    @Test
    public void getValue_oneCharKeyAndValue() {
        environmentVariables = Collections.singletonMap("a", "b");
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build()
                .getValue("a");
        assertThat(value).isEqualTo("b");
    }

    @Test
    public void getValue_longKeyAndValue() {
        String longString = FunnyChars.LONG_STRING.getString();
        environmentVariables = Collections.singletonMap(longString, longString);
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
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
        environmentVariables = Collections.singletonMap("my_key", "my_value");
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username(username)
                .password(password)
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
        environmentVariables = Collections.singletonMap("my_key", "my_value");
        String value = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username(username)
                .password(password)
                .build()
                .getValue("my_key");
        assertThat(value).isEqualTo("my_value");
    }


    //------------------------------------------------------------------------
    // Exceptions
    //------------------------------------------------------------------------


    @Test
    public void getValue_incorrectCredentials() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        final HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("wrong_password")
                .build();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> reader.getValue("key_name_1"));
    }

    @Test
    public void getValue_noEntryForKey_exception() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        final HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> reader.getValue("incorrect_key"));
    }

    @Test
    public void getValue_incorrectUrl_exception() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        final HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://192.0.2.0:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build();
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> reader.getValue("key_name_1"));
    }

    @Test
    public void getValue_argumentNull() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                reader.getValue((String)null)
        );
    }

    @Test
    public void getValue_argumentEmptyString() {
        environmentVariables = Collections.singletonMap("key_name_1", "key_value_1");
        HttpEnvironmentVariableReader reader = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username")
                .password("my_password")
                .build();
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                reader.getValue("")
        );
    }
}
