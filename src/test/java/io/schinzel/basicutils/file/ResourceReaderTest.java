package io.schinzel.basicutils.file;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


/**
 * Created by Schinzel on 2018-01-13
 */
public class ResourceReaderTest extends ResourceReader {


    @Test
    public void read_ExistingFile_FileContent() {
        String stringRead = ResourceReader
                .read("resource_test_file.txt")
                .asString();
        assertThat(stringRead).isEqualTo("File content");
    }


    @Test
    public void read_ExistingFileInSubDir_FileContent() {
        String stringRead = ResourceReader
                .read("dir/another_resource_test_file.txt")
                .asString();
        assertThat(stringRead).isEqualTo("File content 2");
    }


    @Test
    public void read_ThrowException_Exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                ResourceReader.read("resource_test_file.txt", true)
        ).withMessageStartingWith("Error when reading resource file");
    }
}