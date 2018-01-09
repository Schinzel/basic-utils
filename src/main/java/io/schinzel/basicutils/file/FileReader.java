package io.schinzel.basicutils.file;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import io.schinzel.basicutils.thrower.Thrower;

import java.io.File;
import java.io.IOException;

/**
 * The purpose of this class is to read files.
 * <p>
 * Created by Schinzel on 2018-01-09
 */
public class FileReader {
    private final File mFile;


    private FileReader(File file) {
        Thrower.throwIfVarNull(file, "file");
        Thrower.throwIfFalse(file.exists(), "No such file '" + file.getName() + "'");
        mFile = file;
    }


    public static FileReader create(String fileName) {
        return FileReader.create(new File(fileName));
    }


    public static FileReader create(File file) {
        return new FileReader(file);
    }


    public String readAsString() {
        try {
            return Files.asCharSource(mFile, Charsets.UTF_8).read();
        } catch (IOException e) {
            throw new RuntimeException("Problems when reading file '" + mFile.getName() + "'. " + e.getMessage());
        }
    }


    public byte[] readAsByteArray() {
        try {
            return Files.toByteArray(mFile);
        } catch (IOException e) {
            throw new RuntimeException("Problems when reading file '" + mFile + "'. " + e.getMessage());
        }
    }

}
