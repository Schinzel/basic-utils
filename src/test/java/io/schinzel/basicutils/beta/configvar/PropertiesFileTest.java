package io.schinzel.basicutils.beta.configvar;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class PropertiesFileTest {


    @Test
    public void getProperties_EmptyFileName_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                PropertiesFile.getProperties(""));
    }


    @Test
    public void getProperties_NullFileName_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                PropertiesFile.getProperties(null));
    }


    @Test
    public void getProperties_GetApe_ShouldGetGorilla() {
        String fileName = Str.create()
                .anl("ape=gorilla")
                .anl("bird=falcon")
                .writeToTempFile();
        Map<String, String> properties = PropertiesFile.getProperties(fileName);
        assertThat(properties.get("ape")).isEqualTo("gorilla");
    }


    @Test
    public void getProperties_PropFileHasTwoProps_SizeShouldBeTwo() {
        String fileName = Str.create()
                .anl("ape=gorilla")
                .anl("#Comment")
                .anl("#Comment")
                .anl()
                .anl("bird=falcon")
                .anl("#Comment")
                .anl()
                .writeToTempFile();
        Map<String, String> properties = PropertiesFile.getProperties(fileName);
        assertThat(properties.size()).isEqualTo(2);
    }


    @Test
    public void getProperties_FileDoesNotExist_ShouldGetEmptyProperties() {
        Map<String, String> properties = PropertiesFile.getProperties("i_do_not_exists.properties");
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);
    }


    @Test
    public void getProperties_EmptyFile_ShouldGetEmptyProperties() {
        String fileName = Str.create().writeToTempFile();
        Map<String, String> properties = PropertiesFile.getProperties(fileName);
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);
    }


    @Test
    public void getProperties_PolishChars_ShouldGetTheSameCharsAsWroteToFile() {
        String fileName = Str.create()
                .a("polish=").anl(FunnyChars.POLISH_LETTERS.getString())
                .writeToTempFile();
        Map<String, String> properties = PropertiesFile.getProperties(fileName);
        assertThat(properties.get("polish")).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
    }


    @Test
    public void readPropertiesFile_NoSuchFile_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                PropertiesFile.readPropertiesFile("no_such_file.txt"));
    }

}