package io.schinzel.basicutils.beta.configvar;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;
import static org.assertj.core.api.Assertions.*;


public class ConfigVarTest {


    @Test
    public void getValue_ValueInEnvVarAndInFile_ShouldReturnValueFromEnvVar() {
        Map<String, String> propFromFile = ImmutableMap.<String, String>builder()
                .put("ape", "chimp")
                .build();
        Map<String, String> envVar = ImmutableMap.<String, String>builder()
                .put("ape", "gorilla")
                .build();
        ConfigVar configVar = new ConfigVar("anyfile.txt", propFromFile, envVar);
        assertThat(configVar.getValue("ape")).isEqualTo("gorilla");
    }


    @Test
    public void getValue_ValueNotInEnvVarButIsInFile_ShouldReturnValueFromFile() {
    }


    @Test
    public void getValue_ValueNotInEnvVarNotInFile_ThrowException() {
    }
}