package io.schinzel.basicutils.configvar2.readers;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HttpConfigVarReaderTest {

    @Test
    public void builder_urlMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .username("my_username")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build);
    }

    @Test
    public void builder_usernameMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .url("http://127.0.0.1:7070/getConfigVar")
                .password("my_password");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build
                );
    }

    @Test
    public void builder_passwordMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .url("http://127.0.0.1:7070/getConfigVar")
                .username("my_username");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build
                );
    }
}