package io.schinzel.basicutils.str;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IStrOutputTest {
    private String mFileName;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private class StrOutput extends AbstractIStr<StrOutput> implements IStrOutput<StrOutput> {
        @Override
        public StrOutput getThis() {
            return this;
        }
    }


    @Before
    public void before() {
        mFileName = "TestFile_" + this.getClass().getSimpleName()
                + RandomUtil.getRandomString(5) + ".txt";
    }


    @After
    public void after() {
        File file = new File(this.getFileName());
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Problems when cleaning up temp test file '" + this.getFileName() + "'.");
        }
    }


    private String getFileName() {
        return mFileName;
    }


    @Test
    public void pln() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new StrOutput().a("Monkey!").pln();
        Assert.assertEquals("Monkey!\n", outContent.toString());
        System.setOut(null);
    }


    @Test
    public void plnWithPrefix_StringWithPrefix_ShouldBePrintedToSystemOut() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new StrOutput().a("Gibbon!").plnWithPrefix("Monkey: ");
        Assert.assertEquals("Monkey: Gibbon!\n", outContent.toString());
        System.setOut(null);
    }


    /**
     * Writes to a file and check that get the same string back.
     */
    @Test
    public void writeToFile() throws Exception {
        String fileName = getFileName();
        String strWritten = "chimp";
        new StrOutput().a(strWritten).writeToFile(fileName);
        String strRead = new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
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
        new StrOutput().a(strWritten).writeToFile(fileName);
        new StrOutput().a(strWritten2).writeToFile(fileName);
        String strRead = new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
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
            new StrOutput().a(strWritten).writeToFile(fileName);
            String strRead = new String(Files.readAllBytes(Paths.get(fileName)));
            Assert.assertEquals(strWritten, strRead);
        }
    }


    /**
     * Test append to file.
     */
    @Test
    public void appendToFile() throws Exception {
        String fileName = getFileName();
        String strWritten = "chimp";
        String strWritten2 = "gorilla";
        new StrOutput().a(strWritten).writeToFile(fileName);
        new StrOutput().a(strWritten2).appendToFile(fileName);
        String strRead = new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
        Assert.assertEquals(strWritten + strWritten2, strRead);
    }


    /**
     * Create a test that throws an IOException
     */
    @Test
    public void writeToFile_throwException() {
        exception.expect(RuntimeException.class);
        new StrOutput().a("chimp").writeToFile("");
    }


    @Test
    public void writeToTempFile_NoFileNameArg_ReadFileWithReturnedNameShouldContainSameString() throws Exception {
        String strWritten = RandomUtil.getRandomString(10);
        String fileName = new StrOutput().a(strWritten).writeToTempFile();
        String strRead = new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
        Assert.assertEquals(strWritten, strRead);
    }


    @Test
    public void writeToTempFile_FileNameArg_ReadFileWithReturnedNameShouldContainSameString() throws Exception {
        String strWritten = RandomUtil.getRandomString(10);
        String fileName = RandomUtil.getRandomString(10) + ".txt";
        new StrOutput().a(strWritten).writeToTempFile(fileName);
        String strRead = new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
        Assert.assertEquals(strWritten, strRead);
    }
}