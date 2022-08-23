package io.schinzel.basicutils.env_var.readers;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class SystemEnvVarReaderTest {
    @Test
    public void getValue_AnExistingKey_TheCorrespondingValue() {
        Map<String, String> salary = ImmutableMap.<String, String>builder()
                .put("John", "1000")
                .put("Jane", "1500")
                .build();
        String value = new SystemEnvVarReader(salary).getValue("Jane");
        assertThat(value).isEqualTo("1500");
    }

    @Test
    public void getValue_KeyDoesNotExist_null() {
        Map<String, String> salary = ImmutableMap.<String, String>builder()
                .put("John", "1000")
                .put("Jane", "1500")
                .build();
        String value = new SystemEnvVarReader(salary).getValue("Thomas");
        assertThat(value).isNull();
    }
}