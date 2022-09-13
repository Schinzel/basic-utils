package io.schinzel.basicutils.env_var_reader;

import io.schinzel.basicutils.configvar.IName;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MultiEnvironmentVariableReaderTest {

    @Test
    public void getValue_2readersKeyIsInBothReaders_returnValueFromFirst() {
        TestReader reader1 = new TestReader()
                .add("ape", "chimp");
        TestReader reader2 = new TestReader()
                .add("ape", "gorilla");
        String value = MultiEnvironmentVariableReader.builder()
                .varReader(reader1)
                .varReader(reader2)
                .build()
                .getValue("ape");
        assertThat(value).isEqualTo("chimp");
    }


    @Test
    public void getValue_2readersKeyIsInSecondReader_returnValueFromFirst() {
        TestReader reader1 = new TestReader()
                .add("ape", "chimp");
        TestReader reader2 = new TestReader()
                .add("monkey", "gibbon");
        String value = MultiEnvironmentVariableReader.builder()
                .varReader(reader1)
                .varReader(reader2)
                .build()
                .getValue("monkey");
        assertThat(value).isEqualTo("gibbon");
    }


    @Test
    public void getValue_2readersNoReaderHasKey_null() {
        TestReader reader1 = new TestReader()
                .add("ape", "chimp");
        TestReader reader2 = new TestReader()
                .add("monkey", "gibbon");
        String value = MultiEnvironmentVariableReader.builder()
                .varReader(reader1)
                .varReader(reader2)
                .build()
                .getValue("no_such_key");
        assertThat(value).isNull();
    }


    @Test
    public void getValue_noReaders_exception() {
        String value = MultiEnvironmentVariableReader.builder()
                .build()
                .getValue("no_such_key");
        assertThat(value).isNull();
    }


    @Test
    public void getValue_ValueIsEmptyValuePlaceHolder_EmptyString() {
        TestReader reader = new TestReader()
                .add("ape", "chimp")
                .add("bird", "#EMPTY#");
        String value = MultiEnvironmentVariableReader.builder()
                .varReader(reader)
                .build()
                .getValue("bird");
        assertThat(value).isEmpty();
    }


    @Test
    public void getValueAsStr_ExistingKey_ValueAsStr() {
        TestReader reader = new TestReader()
                .add("ape", "chimp");
        Str value = MultiEnvironmentVariableReader.builder()
                .varReader(reader)
                .build()
                .getValueAsStr("ape");
        assertThat(value).isOfAnyClassIn(Str.class);
    }


    @Test
    public void getValue_KeyAsIName_CorrectValue() {
        class ApeName implements IName {
            @Override
            public String getMyName() {
                return "ape";
            }
        }

        TestReader reader = new TestReader()
                .add("ape", "chimp");
        String value = MultiEnvironmentVariableReader.builder()
                .varReader(reader)
                .build()
                .getValue(new ApeName());
        assertThat(value).isEqualTo("chimp");
    }


    private static class TestReader implements IEnvironmentVariableReader {
        private final Map<String, String> mProperties = new HashMap<>();

        TestReader add(String keyName, String value) {
            mProperties.put(keyName, value);
            return this;
        }

        @Override
        public String getValue(String key) {
            return mProperties.get(key);
        }
    }
}