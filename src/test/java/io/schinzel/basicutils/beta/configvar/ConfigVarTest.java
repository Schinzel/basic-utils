package io.schinzel.basicutils.beta.configvar;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class ConfigVarTest {


    @Test
    public void getValue_ValueInBothEnvVarAndInPropFromFile_ShouldReturnValueFromEnvVar() {
        Map<String, String> envVar = Collections.singletonMap("ape", "gorilla");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("anyfile.txt", envVar, propFromFile);
        assertThat(configVar.getValue("ape")).isEqualTo("gorilla");
    }


    @Test
    public void getValue_ValueNotInEnvVarButIsPropFromFile_ShouldReturnValueFromFile() {
        Map<String, String> envVar = Collections.singletonMap("bird", "hawk");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("anyfile.txt", envVar, propFromFile);
        assertThat(configVar.getValue("ape")).isEqualTo("chimp");
    }


    @Test
    public void getValue_ValueNotInEnvVarNorInPropFromFile_ThrowException() {
        Map<String, String> envVar = Collections.singletonMap("ape", "gorilla");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("anyfile.txt", envVar, propFromFile);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                configVar.getValue("bird"));
    }
}