package io.schinzel.basicutils.configvar;

import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;


public class ConfigVarTest {


    @Test
    public void create_SetConfigFile_ShouldReadFromFile() {
        String fileName = Str.create().anl("ape=gibbon").writeToTempFile();
        String valFromFile = ConfigVar.create(fileName).getValue("ape");
        assertThat(valFromFile).isEqualTo("gibbon");
    }


    @Test
    public void getValue_ValueInBothEnvVarAndInPropFromFile_ShouldReturnValueFromEnvVar() {
        Map<String, String> envVar = Collections.singletonMap("ape", "gorilla");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("any_file.txt", envVar, propFromFile);
        assertThat(configVar.getValue("ape")).isEqualTo("gorilla");
    }


    @Test
    public void getValue_ValueNotInEnvVarButIsPropFromFile_ShouldReturnValueFromFile() {
        Map<String, String> envVar = Collections.singletonMap("bird", "hawk");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("any_file.txt", envVar, propFromFile);
        assertThat(configVar.getValue("ape")).isEqualTo("chimp");
    }


    @Test
    public void getValue_ValueNotInEnvVarNorInPropFromFile_ThrowException() {
        Map<String, String> envVar = Collections.singletonMap("ape", "gorilla");
        Map<String, String> propFromFile = Collections.singletonMap("ape", "chimp");
        ConfigVar configVar = new ConfigVar("any_file.txt", envVar, propFromFile);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                configVar.getValue("bird"));
    }


    @Test
    public void getValue_KeyInEnvironmentFromFileHasEmptyValuePlaceHolder_EmptyString() {
        Map<String, String> envVar = Collections.singletonMap("ape", "gibbon");
        Map<String, String> propFromFile = Collections.singletonMap("bear", ConfigVar.EMPTY_VALUE_PLACEHOLDER);
        String apeValue = new ConfigVar("any_file.txt", envVar, propFromFile)
                .getValue("bear");
        assertThat(apeValue).isEmpty();
    }


    @Test
    public void getValue_KeyInPropertiesFileHasEmptyValuePlaceHolder_EmptyString() {
        Map<String, String> envVar = Collections.singletonMap("ape", ConfigVar.EMPTY_VALUE_PLACEHOLDER);
        Map<String, String> propFromFile = Collections.singletonMap("bear", "black bear");
        String apeValue = new ConfigVar("any_file.txt", envVar, propFromFile)
                .getValue("ape");
        assertThat(apeValue).isEmpty();
    }


    @Test
    public void Constructor_NoSuchFile_DoesNotTrowException() {
        assertThatCode(() -> new ConfigVar("no_such_file.txt"))
                .doesNotThrowAnyException();
    }


    @Test
    public void getValue_FileWithPropertyExistsContainsProperty_ShouldReturnPropValue() {
        String fileName = Str.create().anl("ape=gibbon").writeToTempFile();
        ConfigVar configVar = new ConfigVar(fileName);
        String actual = configVar.getValue("ape");
        assertThat(actual).isEqualTo("gibbon");
    }


    @Test
    public void getValue_FileWithPropertyExistsDoesNotContainProp_ThrowsException() {
        String fileName = Str.create().anl("ape=gibbon").writeToTempFile();
        ConfigVar configVar = new ConfigVar(fileName);
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                configVar.getValue("bird"));
    }


    @Test
    public void getValue_EnumAsArgument_TheValueOfTheEnumName() {
        String fileName = Str.create().anl("BEAR=4feet\nEAGLE=2feet").writeToTempFile();
        String valFromFile = ConfigVar.create(fileName).getValue(ConfigVarNames.BEAR);
        assertThat(valFromFile).isEqualTo("4feet");
    }
}