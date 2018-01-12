package io.schinzel.basicutils.file;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import io.schinzel.basicutils.UTF8;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@Accessors(prefix = "m")
public class FileReaderTest {
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
                FileReader.readAsString(file)
        ).withMessageStartingWith("Argument 'file' cannot be null");
    }


    @Test
    public void readAsString_NonExistingFile_Exception() {
        File file = new File("I_do_not_exist.txt");
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(file)
        ).withMessageStartingWith("Error reading file. File 'I_do_not_exist.txt' does not exist");
    }


    @Test
    public void readAsString_Dir_Exception() {
        File file = new File("/");
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(file)
        ).withMessageStartingWith("Argument file '/' is not a file.");
    }


    @Test
    public void readAsString_NullFileName_Exception() {
        String fileName = null;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void readAsString_EmptyFileName_Exception() {
        String fileName = "";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void readAsString_FileDoesNotExists_Exception() {
        String fileName = "I_do_not_exist.txt";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Error reading file. File 'I_do_not_exist.txt' does not exist");
    }


    @Test
    public void readAsString_ExistingFileWithArabicChars_ReadStringShouldHaveTheCorrectChars() {
        String stringToWrite = FunnyChars.ARABIC_LETTERS.getString();
        String fileName = FileWriter.writeToTempFile(stringToWrite);
        String readString = FileReader.readAsString(fileName);
        assertThat(readString).isEqualTo(stringToWrite);
    }


    @Test
    public void readAsByteArray_NullFileName_Exception() {
        String fileName = null;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");

    }


    @Test
    public void readAsByteArray_EmptyFileName_Exception() {
        String fileName = "";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void readAsByteArray_FileDoesNotExist_Exception() {
        String fileName = "I_do_not_exist.txt";
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsString(fileName)
        ).withMessageStartingWith("Error reading file. File 'I_do_not_exist.txt' does not exist");
    }


    @Test
    public void readAsByteArray_EmulatedIOException_Exception() {
        String fileName = this.getFileName();
        FileWriter.write(fileName, "any content");
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileReader.readAsByteArray(fileName, true)
        );
    }


    @Test
    public void readAsByteArray_FileWithPersianChars_ReadStringShouldHaveTheCorrectChars() {
        String stringToWrite = FunnyChars.PERSIAN_LETTERS.getString();
        String fileName = FileWriter.writeToTempFile(stringToWrite);
        byte[] bytesRead = FileReader.readAsByteArray(fileName);
        String stringRead = UTF8.getString(bytesRead);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }
}