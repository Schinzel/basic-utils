package io.schinzel.basicutils.beta.configvar;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import java.io.File;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class PropertiesFileTest {


    @Test
    public void getProperties_EmptyFileName_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            PropertiesFile.getProperties("");
        });
    }


    @Test
    public void getProperties_NullFileName_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            PropertiesFile.getProperties(null);
        });
    }


    @Test
    public void getProperties_GetApe_ShouldGetGorilla() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create()
                .anl("ape=gorilla")
                .anl("bird=falcon")
                .writeToTempFile(filename);
        Map<String, String> properties = PropertiesFile.getProperties(filename);
        assertThat(properties.get("ape")).isEqualTo("gorilla");
        new File(filename).delete();
    }


    @Test
    public void getProperties_PropFileHasTwoProps_SizeShouldBeTwo() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create()
                .anl("ape=gorilla")
                .anl("#Comment")
                .anl("#Comment")
                .anl()
                .anl("bird=falcon")
                .anl("#Comment")
                .anl()
                .writeToTempFile(filename);
        Map<String, String> properties = PropertiesFile.getProperties(filename);
        assertThat(properties.size()).isEqualTo(2);
        //new File(filename).delete();
    }


    @Test
    public void getProperties_FileDoesNotExist_ShouldGetEmptyProperties() {
        Map<String, String> properties = PropertiesFile.getProperties("i_do_not_exists.properties");
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);

    }


    @Test
    public void getProperties_EmptyFile_ShouldGetEmptyProperties() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create().writeToTempFile(filename);
        Map<String, String> properties = PropertiesFile.getProperties(filename);
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);
    }


    @Test
    public void getProperties_PolishChars_ShouldGetTheSameCharsAsWroteToFile() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create()
                .a("polish=").anl(FunnyChars.POLISH_LETTERS.getString())
                .writeToTempFile(filename);
        Map<String, String> properties = PropertiesFile.getProperties(filename);
        assertThat(properties.get("polish")).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
        new File(filename).delete();
    }

}