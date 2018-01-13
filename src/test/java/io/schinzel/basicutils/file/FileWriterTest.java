package io.schinzel.basicutils.file;

import io.schinzel.basicutils.EmptyObjects;
import io.schinzel.basicutils.RandomUtil;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * The purpose of this class
 *
 * @author Schinzel
 */
@SuppressWarnings("ConstantConditions")
@Accessors(prefix = "m")
public class FileWriterTest {
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
    public void writeToFile_NullFileName_Exception() {
        String fileName = null;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileWriter.writeToFile(fileName, EmptyObjects.EMPTY_STRING, FileWriter.FileOp.WRITE)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void writeToFile_EmptyFileName_Exception() {
        String fileName = EmptyObjects.EMPTY_STRING;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileWriter.writeToFile(fileName, EmptyObjects.EMPTY_STRING, FileWriter.FileOp.WRITE)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void writeToFile_NullString_WritesEmptyString() {
        String fileName = this.getFileName();
        String stringToWrite = null;
        FileWriter.writeToFile(fileName, stringToWrite, FileWriter.FileOp.WRITE);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEmpty();
    }


    @Test
    public void append_NonExistingFile_WriteWorks() {
        String fileName = this.getFileName();
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.append(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


    @Test
    public void append_ExistingEmptyFile_WriteWorks() {
        String fileName = this.getFileName();
        FileWriter.write(fileName, EmptyObjects.EMPTY_STRING);
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.append(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


    @Test
    public void append_ExistingNonEmptyFile_WriteWorks() {
        String fileName = this.getFileName();
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.write(fileName, stringToWrite);
        FileWriter.append(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite + stringToWrite);
    }


    @Test
    public void write_ExistingFile_FileOverwritten() {
        String fileName = this.getFileName();
        FileWriter.write(fileName, RandomUtil.getRandomString(10));
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.write(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


    @Test
    public void write_NoSuchFile_FileCreatedAndWrittenTo() {
        String fileName = this.getFileName();
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.write(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


    @Test
    public void writeToTempFile_FileNameAsArgument_FileWithRandomNameCreated() {
        String fileName = this.getFileName();
        String stringToWrite = RandomUtil.getRandomString(100);
        FileWriter.writeToTempFile(fileName, stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


    @Test
    public void writeToTempFile_RandomFileName_FileWithRandomNameCreated() {
        String stringToWrite = RandomUtil.getRandomString(100);
        String fileName = FileWriter.writeToTempFile(stringToWrite);
        String stringRead = FileReader.read(fileName);
        assertThat(stringRead).isEqualTo(stringToWrite);
    }


}