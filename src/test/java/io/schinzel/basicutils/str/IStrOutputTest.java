package io.schinzel.basicutils.str;

import com.google.common.io.Files;
import io.schinzel.basicutils.FunnyChars;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

/**
 * Created by schinzel on 2017-02-27.
 */
public class IStrOutputTest {

    private class StrString extends AbstractIStr<StrString> implements IStrOutput<StrString> {
        @Override
        public StrString getThis() {
            return this;
        }
    }


    static String getFileName() {
        String tempDir = System.getProperty("java.io.tmpdir");
        return tempDir + "MyFile.txt";
    }


    @Test
    public void pln() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new StrString().a("Monkey!").pln();
        Assert.assertEquals("Monkey!\n", outContent.toString());
        System.setOut(null);
    }


    /**
     * Writes to a file and check that get the same string back.
     */
    @Test
    public void writeToFile() throws Exception {
        String fileName = getFileName();
        String strWritten = "chimp";
        new StrString().a(strWritten).writeToFile(fileName);
        String strRead = FileUtils.readFileToString(new File(fileName), IStr.ENCODING);
        Assert.assertEquals(strWritten, strRead);
    }


    /**
     * Writes to a file twice and that file is overwritten.
     */
    @Test
    public void writeToFile_overwrite() throws Exception {
        String fileName = getFileName();
        String strWritten = "chimp";
        String strWritten2 = "gorilla";
        new StrString().a(strWritten).writeToFile(fileName);
        new StrString().a(strWritten2).writeToFile(fileName);
        String strRead = FileUtils.readFileToString(new File(fileName), IStr.ENCODING);
        Assert.assertEquals(strWritten2, strRead);
    }


    /**
     * Writes unusual chars and check that these come back the same.
     */
    @Test
    public void writeToFile_funnyChars() throws Exception {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String fileName = getFileName();
            String strWritten = funnyChars.getString();
            new StrString().a(strWritten).writeToFile(fileName);
            String strRead = FileUtils.readFileToString(new File(fileName), IStr.ENCODING);
            Assert.assertEquals(strWritten, strRead);
        }
    }


    @Test
    public void writeToFile1() throws Exception {
    }

}