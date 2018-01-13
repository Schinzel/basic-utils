package io.schinzel.basicutils.file;

import io.schinzel.basicutils.UTF8;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


/**
 * Created by Schinzel on 2018-01-13
 */
public class ResourceReaderTest {
    private final String mFileName = "resource_test_file.txt";


    @Test
    public void read_ExistingFile_FileContent() {
        String stringRead = ResourceReader.read(mFileName);
        assertThat(stringRead).isEqualTo("File content");
    }


    @Test
    public void read_ExistingFileInSubDir_FileContent() {
        String stringRead = ResourceReader.read("dir/another_resource_test_file.txt");
        assertThat(stringRead).isEqualTo("File content 2");
    }


    @Test
    public void readAsByteArray_ExistingFile_FileContent() {
        byte[] bytesRead = ResourceReader.readAsByteArray(mFileName);
        String stringRead = UTF8.getString(bytesRead);
        assertThat(stringRead).isEqualTo("File content");
    }


    @Test
    public void readAsStr_ExistingFile_FileContent() {
        String stringRead = ResourceReader.readAsStr(mFileName).getString();
        assertThat(stringRead).isEqualTo("File content");
    }


    @Test
    public void readAsByteArrayInternal_ThrowException_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                ResourceReader.readAsByteArrayInternal(mFileName, true)
        ).withMessageStartingWith("Error when reading resource file");
    }
}