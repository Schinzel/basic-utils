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
        String actual = SubString
                .create("aaaSTARTuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelIsFirstCharsAndEndDelIsLastCahrs_StringBetweenStartAndEnd() {
        String expected = "uuu";
        String actual = SubString
                .create("STARTuuuEND")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchEndDelimiter_StringAfterStartDelimiter() {
        String expected = "uuuooo";
        String actual = SubString
                .create("aaaSTARTuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchStartDelimiter_StringBeforeEndDelimiter() {
        String expected = "";
        String actual = SubString
                .create("aaaENDuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_MultipleOccurrencesOfStartAndEndDelimiter_StringBetweenFirstStartAndFirstEndDelimiter() {
        String expected = "iiiSTARTaaa";
        String actual = SubString
                .create("rrrSTARTiiiSTARTaaaENDuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartAndEndAreTheSame_StringFirstAndSecondDelimiter() {
        String expected = "uuu";
        String actual = SubString
                .create("aaaAAAuuuAAAooo")
                .startDelimiter("AAA")
                .endDelimiter("AAA")
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // Test only start delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_StartDelimiterIsFirstChars_StringAfterStartDelimiter() {
        String expected = "uuuooo";
        String actual = SubString
                .create("STARTuuuooo")
                .startDelimiter("START")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_NoSuchStartDelimiter_EmptyString() {
        String expected = "";
        String actual = SubString
                .create("uuuoooiii")
                .startDelimiter("START")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_RequestOccurrenceOfStartDelimiterThatDoesNotExist_EmptyString() {
        String expected = "";
        String actual = SubString
                .create("rrrSTARTiiiSTARTaaaENDuuuENDooo")
                .startDelimiter("START")
                .startDelimiterOccurrence(40)
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterIsLastChars_EmptyString() {
        String expected = "";
        String actual = SubString
                .create("uuuoooSTART")
                .startDelimiter("START")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_SecondStartDelimiterOfThree_StringAfterSecondStartDelimiter() {
        String expected = "eeeSTARTccc";
        String actual = SubString
                .create("uuuSTARToooSTARTeeeSTARTccc")
                .startDelimiter("START")
                .startDelimiterOccurrence(2)
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterIsInMiddle_StringAfterStartDelimiter() {
        String expected = "ooo";
        String actual = SubString
                .create("uuuSTARTooo")
                .startDelimiter("START")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_StartDelimiterOccurrsTwice_StringAfterFirstStartDelimiter() {
        String expected = "oooSTARTiii";
        String actual = SubString
                .create("uuuSTARToooSTARTiii")
                .startDelimiter("START")
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // Test only end delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_TwoEndDelimiter_StringBeforeFirstOccurrence() {
        String expected = "uuu";
        String actual = SubString
                .create("uuuENDoooENDiii")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_SecondEndDelimiterOfThree_StringBeforeSecondOccurrence() {
        String expected = "uuuENDooo";
        String actual = SubString
                .create("uuuENDoooENDiiiENDeee")
                .endDelimiter("END")
                .endDelimiterOccurrence(2)
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_RequestOccurrenceOfEndDelimiterThatDoesNotExist_WholeString() {
        String expected = "uuuENDoooENDiiiENDeee";
        String actual = SubString
                .create("uuuENDoooENDiiiENDeee")
                .endDelimiter("END")
                .endDelimiterOccurrence(40)
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterOneCharLong_StringBeforeEndDelimiter() {
        String expected = "uuu";
        String actual = SubString
                .create("uuuAooo")
                .endDelimiter("A")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterLastChars_StringBeforeEndDelimiter() {
        String expected = "uuuooo";
        String actual = SubString
                .create("uuuoooEND")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterMiddleOfString_StringBeforeEndDelimiter() {
        String expected = "uuu";
        String actual = SubString
                .create("uuuENDooo")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_NoSuchEndDelimiter_WholeString() {
        String expected = "uuuiiiooo";
        String actual = SubString
                .create("uuuiiiooo")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getString_EndDelimiterIsFirstChars_EmptyString() {
        String expected = "";
        String actual = SubString
                .create("ENDuuukkkk")
                .endDelimiter("END")
                .getString();
        assertEquals(expected, actual);
    }
    //------------------------------------------------------------------------
    // MISC
    //------------------------------------------------------------------------


    @Test
    public void getString_NoDelimiters_InputStringUnaltered() {
        String expected = "uuu";
        String actual = SubString
                .create("uuu")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void toString_NoDelimiters_InputStringUnaltered() {
        String expected = "uuu";
        String actual = SubString
                .create("uuu")
                .toString();
        assertEquals(expected, actual);
    }


    @Test
    public void getBuilder_StringWithTwoLevelsOfDelimiters_StringBetween() {
        String expected = "cccc";
        String input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        String actual = SubString
                .create(input)
                .startDelimiter("FIRST_START")
                .endDelimiter("FIRST_END")
                .newSubString()
                .startDelimiter("SECOND_START")
                .endDelimiter("SECOND_END")
                .getString();
        assertEquals(expected, actual);
    }


    @Test
    public void getStr_SetString_ArgumentString() {
        String expected = "uuu";
        String actual = SubString
                .create("uuu")
                .getStr()
                .getString();
        assertEquals(expected, actual);


    }


}
