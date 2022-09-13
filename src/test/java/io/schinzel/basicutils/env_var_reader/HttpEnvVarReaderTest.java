package io.schinzel.basicutils.env_var_reader;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpEnvVarReaderTest {

    @Test
    public void builder_urlMissing() {
        HttpEnvironmentVariableReader.HttpEnvironmentVariableReaderBuilder httpEnvVarReaderBuilder = HttpEnvironmentVariableReader.builder()
                .username("my_username")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build);
    }

    @Test
    public void builder_usernameMissing() {
        HttpEnvironmentVariableReader.HttpEnvironmentVariableReaderBuilder httpEnvVarReaderBuilder = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build
                );
    }

    @Test
    public void builder_passwordMissing() {
        HttpEnvironmentVariableReader.HttpEnvironmentVariableReaderBuilder httpEnvVarReaderBuilder = HttpEnvironmentVariableReader.builder()
                .url("http://127.0.0.1:7070/getEnvVar")
                .username("my_username");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpEnvVarReaderBuilder::build
                );
    }


    @Test
    public void builder_noTimeoutSet_DefaultTimeout(){
        final HttpEnvironmentVariableReader httpEnvVarReader = HttpEnvironmentVariableReader.builder()
                .url("")
                .username("")
                .password("")
                .build();
        assertThat(httpEnvVarReader.timeout).isEqualTo(2_000);
    }


    @Test
    public void builder_timeoutSet_TimeoutIsSetValue(){
        final HttpEnvironmentVariableReader httpEnvVarReader = HttpEnvironmentVariableReader.builder()
                .url("")
                .username("")
                .password("")
                .timeout(3_000)
                .build();
        assertThat(httpEnvVarReader.timeout).isEqualTo(3_000);
    }

}