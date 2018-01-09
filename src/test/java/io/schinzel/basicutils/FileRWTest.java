package io.schinzel.basicutils;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Accessors(prefix = "m")
public class FileRWTest {
    @Getter
    private final String mFileName = "TestFile_"
            + this.getClass().getSimpleName()
            + "_" + RandomUtil.getRandomString(5) + ".txt";


    @After
    public void after() {
        File file = new File(this.getFileName());
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Problems when cleaning up temp test file '" + this.getFileName() + "'.");
        }
    }


    @Test
    public void readAsString_NullFile_Exception() {
        File file = null;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileRW.readAsString(file)
        );
    }


    @Test
    public void readAsString_NonExistingFile_Exception() {
        File file = new File("I_do_not_exist");
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileRW.readAsString(file)
        );
    }


    @Test
    public void readAsString_NullFileName_Exception() {
        String fileName = null;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileRW.readAsString(fileName)
        );
    }


    @Test
    public void readAsString_EmptyFileName_Exception() {
        String fileName = "";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileRW.readAsString(fileName)
        );
    }


    @Test
    public void readAsString_NonExistingFileFromFileName_Exception() {
        String fileName = "I_do_not_exist";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileRW.readAsString(fileName)
        );
    }


    @Test
    public void readAsString_ExistingFileWithArabicChars_ReadStringShouldHaveTheCorrectChars() {
        String stringToWrite = FunnyChars.ARABIC_LETTERS.getString();
        String fileName = FileRW.writeToTempFile(stringToWrite);
        String readString = FileRW.readAsString(fileName);
        assertThat(readString).isEqualTo(stringToWrite);
    }
}