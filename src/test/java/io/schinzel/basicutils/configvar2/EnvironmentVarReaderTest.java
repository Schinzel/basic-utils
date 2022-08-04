package io.schinzel.basicutils.configvar2;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


public class EnvironmentVarReaderTest {
    @Test
    public void getValue_AnExistingKey_TheCorrespondingValue() {
        Map<String, String> salary = ImmutableMap.<String, String>builder()
                .put("John", "1000")
                .put("Jane", "1500")
                .build();
        String value = new EnvironmentVarReader(salary).getValue("Jane");
        assertThat(value).isEqualTo("1500");
    }

    @Test
    public void getValue_KeyDoesNotExist_null() {
        Map<String, String> salary = ImmutableMap.<String, String>builder()
                .put("John", "1000")
                .put("Jane", "1500")
                .build();
        String value = new EnvironmentVarReader(salary).getValue("Thomas");
        assertThat(value).isNull();
    }
}