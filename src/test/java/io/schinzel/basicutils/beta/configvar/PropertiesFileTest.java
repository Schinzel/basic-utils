package io.schinzel.basicutils.beta.configvar;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;


public class PropertiesFileTest {


    @Test
    public void getProperties_GetApe_ShouldGetGorilla() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create()
                .anl("ape=gorilla")
                .anl("bird=falcon")
                .writeToFile(filename);
        Properties properties = PropertiesFile.getProperties(filename);
        assertThat(properties.getProperty("ape")).isEqualTo("gorilla");
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
                .writeToFile(filename);
        Properties properties = PropertiesFile.getProperties(filename);
        assertThat(properties.size()).isEqualTo(2);
        new File(filename).delete();
    }


    @Test
    public void get_FileDoesNotExist_ShouldGetEmptyProperties() {
        Properties properties = PropertiesFile.getProperties("i_do_not_exists.properties");
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);

    }


    @Test
    public void get_EmptyFile_ShouldGetEmptyProperties() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create().writeToFile(filename);
        Properties properties = PropertiesFile.getProperties(filename);
        assertThat(properties).isNotNull();
        assertThat(properties.size()).isEqualTo(0);
        new File(filename).delete();
    }


    @Test
    public void get_PolishChars_ShouldGetTheSameCharsAsWroteToFile() {
        String filename = RandomUtil.getRandomString(5) + ".properties";
        Str.create()
                .a("polish=").anl(FunnyChars.POLISH_LETTERS.getString())
                .writeToFile(filename);
        Properties properties = PropertiesFile.getProperties(filename);
        assertThat(properties.getProperty("polish")).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
        new File(filename).delete();
    }

}