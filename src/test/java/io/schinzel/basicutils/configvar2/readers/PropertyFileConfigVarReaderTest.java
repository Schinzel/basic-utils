package io.schinzel.basicutils.configvar2.readers;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.str.Str;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PropertyFileConfigVarReaderTest {

    //------------------------------------------------------------------------
    // constructor
    //------------------------------------------------------------------------

    @Test
    public void constructor_fileDoesNotExist_exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                new PropertyFileConfigVarReader("no_such_file"));
    }

    @Test
    public void constructor_emptyFileName_exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                new PropertyFileConfigVarReader(""));
    }

    @Test
    public void constructor_nullFileName_exception() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                new PropertyFileConfigVarReader(null));
    }


    //------------------------------------------------------------------------
    // getValue
    //------------------------------------------------------------------------

    @Test
    public void getValue_Ape_Gorilla() {
        String fileName = Str.create()
                .anl("ape=gorilla")
                .anl("bird=falcon")
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue("ape");
        assertThat(value).isEqualTo("gorilla");
    }

    @Test
    public void getValue_ApeButApeExistsTwiceInFile_LastValue() {
        String fileName = Str.create()
                .anl("ape=gorilla")
                .anl("bird=falcon")
                .anl("ape=chimp")
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue("ape");
        assertThat(value).isEqualTo("chimp");
    }

    @Test
    public void getValue_PolishChars_ShouldGetTheSameCharsAsWroteToFile() {
        String fileName = Str.create()
                .a("źż=").anl(FunnyChars.POLISH_LETTERS.getString())
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName)
                .getValue("źż");
        assertThat(value).isEqualTo(FunnyChars.POLISH_LETTERS.getString());
    }

    @Test
    public void getValue_EmptyValue_EmptyString() {
        String fileName = Str.create()
                .anl("ape=")
                .anl("bird=falcon")
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue("ape");
        assertThat(value).isEmpty();
    }

    @Test
    public void getValue_OneCharKeyAndValue_EmptyString() {
        String fileName = Str.create()
                .anl("a=b")
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue("a");
        assertThat(value).isEqualTo("b");
    }

    @Test
    public void getValue_NoSuchValue_Null() {
        String fileName = Str.create()
                .anl("a=b")
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue("no_such_key");
        assertThat(value).isNull();
    }

    @Test
    public void getValue_LongKeyAndValue_Null() {
        String longString = FunnyChars.LONG_STRING.getString();
        String fileName = Str.create()
                .anl(longString + "=" + longString)
                .writeToTempFile();
        String value = new PropertyFileConfigVarReader(fileName).getValue(longString);
        assertThat(value).isEqualTo(longString);
    }


    //------------------------------------------------------------------------
    // getProperties
    //------------------------------------------------------------------------

    @Test
    public void getProperties_PropFileHasTwoPropsAndCommentsAndBlankLines_SizeShouldBeTwo() {
        String fileName = Str.create()
                .anl("ape=gorilla")
                .anl("#Comment")
                .anl("#Comment")
                .anl()
                .anl("bird=falcon")
                .anl("#Comment")
                .anl()
                .writeToTempFile();
        Integer numberOfProperties = PropertyFileConfigVarReader.getProperties(fileName)
                .size();
        assertThat(numberOfProperties).isEqualTo(2);
    }

    @Test
    public void getProperties_emptyFile_emptyMap(){
        String fileName = Str.create()
                .writeToTempFile();
        Integer numberOfProperties = PropertyFileConfigVarReader.getProperties(fileName)
                .size();
        assertThat(numberOfProperties).isZero();
    }

    @Test
    public void getProperties_fileWithJustCommentsAndEmptyLines_emptyMap(){
        String fileName = Str.create()
                .anl("#Comment")
                .anl("#Comment")
                .anl()
                .anl("#Comment")
                .anl()
                .writeToTempFile();
        Integer numberOfProperties = PropertyFileConfigVarReader.getProperties(fileName)
                .size();
        assertThat(numberOfProperties).isZero();
    }
}
