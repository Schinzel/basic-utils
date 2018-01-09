package io.schinzel.basicutils.str;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.RandomUtil;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@Accessors(prefix = "m")
public class IStrOutputTest {
    @Getter
    private final String mFileName = "TestFile_"
            + this.getClass().getSimpleName()
            + "_" + RandomUtil.getRandomString(5) + ".txt";


    private class StrOutput extends AbstractIStr<StrOutput> implements IStrOutput<StrOutput> {
        @Override
        public StrOutput getThis() {
            return this;
        }
    }


    @After
    public void after() {
        File file = new File(this.getFileName());
        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Problems when cleaning up temp test file '" + this.getFileName() + "'.");
        }
    }


    @SneakyThrows
    private String readFile(String fileName) {
        return new String(Files.readAllBytes(Paths.get(fileName)), IStr.ENCODING);
    }


    @Test
    public void writeToSystemOut_PolishChars_CharsOnSystemOutShouldBePolishChars() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String stringWritten = FunnyChars.POLISH_LETTERS.getString();
        new StrOutput().a(stringWritten).writeToSystemOut();
        assertThat(outContent.toString()).isEqualTo(stringWritten + "\n");
        System.setOut(null);
    }


    @Test
    public void writeToSystemOutWithPrefix_StringWithPrefix_ShouldBePrintedToSystemOut() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new StrOutput().a("Gibbon!").writeToSystemOutWithPrefix("Monkey: ");
        assertThat(outContent.toString()).isEqualTo("Monkey: Gibbon!\n");
        System.setOut(null);
    }


    @Test
    public void writeToFile_CyrillicChars_CharsInFileShouldBeCyrillicChars() throws Exception {
        String fileName = getFileName();
        String strWritten = FunnyChars.CYRILLIC_LETTERS.getString();
        new StrOutput().a(strWritten).writeToFile(fileName);
        String strRead = this.readFile(fileName);
        assertThat(strRead).isEqualTo(strWritten);
    }


    @Test
    public void writeToFile_WriteToFileTwice_ContentShouldBeContentOfSecondWriteOnly() throws Exception {
        String fileName = getFileName();
        String stringToWrite1 = "chimp";
        String stringToWrite2 = "gorilla";
        new StrOutput().a(stringToWrite1).writeToFile(fileName);
        new StrOutput().a(stringToWrite2).writeToFile(fileName);
        String strRead = this.readFile(fileName);
        assertThat(strRead).isEqualTo(stringToWrite2);
    }


    @Test
    public void writeToFile_WriteFunnyChars_FileShouldContainCorrectWrittenChars() throws Exception {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String fileName = getFileName();
            String strWritten = funnyChars.getString();
            new StrOutput().a(strWritten).writeToFile(fileName);
            String strRead = this.readFile(fileName);
            assertThat(strRead).isEqualTo(strWritten);
        }
    }


    @Test
    public void appendToFile_AppendToExistingFile_FileShouldContainOriginalAndAppendedString() throws Exception {
        String fileName = getFileName();
        String strWritten = "chimp";
        String strWritten2 = "gorilla";
        new StrOutput().a(strWritten).writeToFile(fileName);
        new StrOutput().a(strWritten2).appendToFile(fileName);
        String strRead = this.readFile(fileName);
        assertThat(strRead).isEqualTo(strWritten + strWritten2);
    }


    @Test
    public void writeToFile_throwException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                new StrOutput().a("chimp").writeToFile("")
        );
    }


    @Test
    public void writeToTempFile_NoFileNameArg_ReadFileWithReturnedNameShouldContainSameString() throws Exception {
        String strWritten = RandomUtil.getRandomString(10);
        String fileName = new StrOutput().a(strWritten).writeToTempFile();
        String strRead = this.readFile(fileName);
        assertThat(strRead).isEqualTo(strWritten);
    }


    @Test
    public void writeToTempFile_FileNameArg_ReadFileWithReturnedNameShouldContainSameString() throws Exception {
        String strWritten = RandomUtil.getRandomString(10);
        String fileName = RandomUtil.getRandomString(10) + ".txt";
        new StrOutput().a(strWritten).writeToTempFile(fileName);
        String strRead = this.readFile(fileName);
        assertThat(strRead).isEqualTo(strWritten);
    }


}