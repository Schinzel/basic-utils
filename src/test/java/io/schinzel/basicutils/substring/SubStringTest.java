package io.schinzel.basicutils.substring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * @author Schinzel
 */
public class SubStringTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();
    //------------------------------------------------------------------------
    // Test start and end delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_StartAndEndDelimiter_StringBetweenStartAndEnd() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("aaaSTARTuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelIsFirstCharsAndEndDelIsLastCahrs_StringBetweenStartAndEnd() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("STARTuuuEND")
                .startDelimiter("START")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchEndDelimiter_StringAfterStartDelimiter() {
        String expected = "uuuooo";
        String actual = SubString.builder()
                .string("aaaSTARTuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchStartDelimiter_StringBeforeEndDelimiter() {
        String expected = "";
        String actual = SubString.builder()
                .string("aaaENDuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_MultipleOccurrencesOfStartAndEndDelimiter_StringBetweenFirstStartAndFirstEndDelimiter() {
        String expected = "iiiSTARTaaa";
        String actual = SubString.builder()
                .string("rrrSTARTiiiSTARTaaaENDuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndAreTheSame_StringFirstAndSecondDelimiter() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("aaaAAAuuuAAAooo")
                .startDelimiter("AAA")
                .endDelimiter("AAA")
                .build()
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // Test only start delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_StartDelimiterIsFirstChars_StringAfterStartDelimiter() {
        String expected = "uuuooo";
        String actual = SubString.builder()
                .string("STARTuuuooo")
                .startDelimiter("START")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_NoSuchStartDelimiter_EmptyString() {
        String expected = "";
        String actual = SubString.builder()
                .string("uuuoooiii")
                .startDelimiter("START")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterIsLastChars_EmptyString() {
        String expected = "";
        String actual = SubString.builder()
                .string("uuuoooSTART")
                .startDelimiter("START")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_SecondStartDelimiterOfThree_StringAfterSecondStartDelimiter() {
        String expected = "eeeSTARTccc";
        String actual = SubString.builder()
                .string("uuuSTARToooSTARTeeeSTARTccc")
                .startDelimiter("START")
                .startOccurrence(2)
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterIsInMiddle_StringAfterStartDelimiter() {
        String expected = "ooo";
        String actual = SubString.builder()
                .string("uuuSTARTooo")
                .startDelimiter("START")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterOccurrsTwice_StringAfterFirstStartDelimiter() {
        String expected = "oooSTARTiii";
        String actual = SubString.builder()
                .string("uuuSTARToooSTARTiii")
                .startDelimiter("START")
                .build()
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // Test only end delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_TwoEndDelimiter_StringBeforeFirstOccurrence() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("uuuENDoooENDiii")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_SecondEndDelimiterOfThree_StringBeforeSecondOccurrence() {
        String expected = "uuuENDooo";
        String actual = SubString.builder()
                .string("uuuENDoooENDiiiENDeee")
                .endDelimiter("END")
                .endOccurrence(2)
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterOneCharLong_StringBeforeEndDelimiter() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("uuuAooo")
                .endDelimiter("A")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterLastChars_StringBeforeEndDelimiter() {
        String expected = "uuuooo";
        String actual = SubString.builder()
                .string("uuuoooEND")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterMiddleOfString_StringBeforeEndDelimiter() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("uuuENDooo")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_NoSuchEndDelimiter_WholeString() {
        String expected = "uuuiiiooo";
        String actual = SubString.builder()
                .string("uuuiiiooo")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterIsFirstChars_EmptyString() {
        String expected = "";
        String actual = SubString.builder()
                .string("ENDuuukkkk")
                .endDelimiter("END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // MISC
    //------------------------------------------------------------------------


    @Test
    public void getString_NoDelimiters_InputStringUnaltered() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("uuu")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getBuilder_StringWithTwoLevelsOfDelimiters_StringBetween() {
        String expected = "cccc";
        String input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        String actual = SubString.builder()
                .string(input)
                .startDelimiter("FIRST_START")
                .endDelimiter("FIRST_END")
                .build()
                .getBuilder()
                .startDelimiter("SECOND_START")
                .endDelimiter("SECOND_END")
                .build()
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getStr_SetString_ArgumentString() {
        String expected = "uuu";
        String actual = SubString.builder()
                .string("uuu")
                .build()
                .getStr()
                .getString();
        assertEquals(expected, actual);


    }


}
