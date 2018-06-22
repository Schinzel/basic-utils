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
 *
 * @author Schinzel
 */
@SuppressWarnings("ConstantConditions")
@Accessors(prefix = "m")
public class FileWriterTest extends FileWriter {
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
                FileWriter.writeToFile(fileName, EmptyObjects.EMPTY_STRING, FileWriter.FileOp.WRITE, FileWriter.DeleteOnExit.FALSE)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void writeToFile_EmptyFileName_Exception() {
        String fileName = EmptyObjects.EMPTY_STRING;
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                FileWriter.writeToFile(fileName, EmptyObjects.EMPTY_STRING, FileWriter.FileOp.WRITE, FileWriter.DeleteOnExit.FALSE)
        ).withMessageStartingWith("Argument 'fileName' cannot be empty");
    }


    @Test
    public void writeToFile_NullString_WritesEmptyString() {
        String fileName = this.getFileName();
        String content = null;
        FileWriter.writeToFile(fileName, content, FileWriter.FileOp.WRITE, FileWriter.DeleteOnExit.FALSE);
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEmpty();
    }


    @Test
    public void append_NonExistingFile_WriteWorks() {
        String fileName = this.getFileName();
        String content = RandomUtil.getRandomString(100);
        FileWriter.appender()
                .fileName(fileName)
                .content(content)
                .append();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


    @Test
    public void append_ExistingEmptyFile_WriteWorks() {
        String fileName = this.getFileName();
        FileWriter.writer()
                .fileName(fileName)
                .content(EmptyObjects.EMPTY_STRING)
                .write();
        String content = RandomUtil.getRandomString(100);
        FileWriter.appender()
                .fileName(fileName)
                .content(content)
                .append();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


    @Test
    public void append_ExistingNonEmptyFile_WriteWorks() {
        String fileName = this.getFileName();
        String content = RandomUtil.getRandomString(100);
        FileWriter.writer()
                .fileName(fileName)
                .content(content)
                .write();
        FileWriter.appender()
                .fileName(fileName)
                .content(content)
                .append();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content + content);
    }


    @Test
    public void write_ExistingFile_FileOverwritten() {
        String fileName = this.getFileName();
        FileWriter.writer()
                .fileName(fileName)
                .content(RandomUtil.getRandomString(10))
                .write();
        String content = RandomUtil.getRandomString(100);
        FileWriter.writer()
                .fileName(fileName)
                .content(content)
                .write();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


    @Test
    public void write_NoSuchFile_FileCreatedAndWrittenTo() {
        String fileName = this.getFileName();
        String content = RandomUtil.getRandomString(100);
        FileWriter.writer()
                .fileName(fileName)
                .content(content)
                .write();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


    @Test
    public void writeToTempFile_FileNameAsArgument_FileWithCreated() {
        String fileName = this.getFileName();
        String content = RandomUtil.getRandomString(100);
        FileWriter.tempFileWriter()
                .fileName(fileName)
                .content(content)
                .write();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


    @Test
    public void writeToTempFile_RandomFileName_FileWithRandomNameCreated() {
        String content = RandomUtil.getRandomString(100);
        String fileName = FileWriter.tempFileWriter()
                .content(content)
                .write();
        String stringRead = FileReader
                .read(fileName)
                .asString();
        assertThat(stringRead).isEqualTo(content);
    }


}