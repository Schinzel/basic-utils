package io.schinzel.basicutils.env_var.readers;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HttpEnvVarReaderTest {

    @Test
    public void builder_urlMissing() {
        HttpEnvVarReader.HttpEnvVarReaderBuilder httpEnvVarReaderBuilder = HttpEnvVarReader.builder()
                .username("my_username")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build);
    }

    @Test
    public void builder_usernameMissing() {
        HttpEnvVarReader.HttpEnvVarReaderBuilder httpEnvVarReaderBuilder = HttpEnvVarReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build
                );
    }

    @Test
    public void builder_passwordMissing() {
        HttpEnvVarReader.HttpEnvVarReaderBuilder httpEnvVarReaderBuilder = HttpEnvVarReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build
                );
    }
}