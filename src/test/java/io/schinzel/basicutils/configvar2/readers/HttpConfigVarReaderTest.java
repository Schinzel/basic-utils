package io.schinzel.basicutils.configvar2.readers;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HttpConfigVarReaderTest {

    @Test
    public void builder_baseUrlMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .username("my_username")
                .password("my_password")
                .variableName("key_name");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build);
    }

    @Test
    public void builder_usernameMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .password("my_password")
                .variableName("key_name");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build
                );
    }

    @Test
    public void builder_passwordMissing() {
        HttpConfigVarReader.HttpConfigVarReaderBuilder httpConfigVarReaderBuilder = HttpConfigVarReader.builder()
                .baseUrl("http://127.0.0.1:7070/getConfigVar")
                .username("my_username")
                .variableName("key_name");
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(httpConfigVarReaderBuilder::build
                );
    }
}