package io.schinzel.basicutils.substring;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;


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
        String actual = SubString
                .create("aaaSTARTuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getString_StartDelIsFirstCharsAndEndDelIsLastCahrs_StringBetweenStartAndEnd() {
        String actual = SubString
                .create("STARTuuuEND")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchEndDelimiter_StringAfterStartDelimiter() {
        String actual = SubString
                .create("aaaSTARTuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuuooo");
    }


    @Test
    public void getString_StartAndEndDelimiterButNoSuchStartDelimiter_StringBeforeEndDelimiter() {
        String actual = SubString
                .create("aaaENDuuuooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("");
    }


    @Test
    public void getString_MultipleOccurrencesOfStartAndEndDelimiter_StringBetweenFirstStartAndFirstEndDelimiter() {
        String actual = SubString
                .create("rrrSTARTiiiSTARTaaaENDuuuENDooo")
                .startDelimiter("START")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("iiiSTARTaaa");
    }


    @Test
    public void getString_StartAndEndAreTheSame_StringFirstAndSecondDelimiter() {
        String actual = SubString
                .create("aaaAAAuuuAAAooo")
                .startDelimiter("AAA")
                .endDelimiter("AAA")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }
    //------------------------------------------------------------------------
    // Test only start delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_StartDelimiterIsFirstChars_StringAfterStartDelimiter() {
        String actual = SubString
                .create("STARTuuuooo")
                .startDelimiter("START")
                .getString();
        assertThat(actual).isEqualTo("uuuooo");
    }


    @Test
    public void getString_NoSuchStartDelimiter_EmptyString() {
        String actual = SubString
                .create("uuuoooiii")
                .startDelimiter("START")
                .getString();
        assertThat(actual).isEqualTo("");
    }


    @Test
    public void getString_RequestOccurrenceOfStartDelimiterThatDoesNotExist_EmptyString() {
        String actual = SubString
                .create("rrrSTARTiiiSTARTaaaENDuuuENDooo")
                .startDelimiter("START")
                .startDelimiterOccurrence(40)
                .getString();
        assertThat(actual).isEqualTo("");
    }


    @Test
    public void getString_StartDelimiterIsLastChars_EmptyString() {
        String actual = SubString
                .create("uuuoooSTART")
                .startDelimiter("START")
                .getString();
        assertThat(actual).isEqualTo("");
    }


    @Test
    public void getString_SecondStartDelimiterOfThree_StringAfterSecondStartDelimiter() {
        String actual = SubString
                .create("uuuSTARToooSTARTeeeSTARTccc")
                .startDelimiter("START")
                .startDelimiterOccurrence(2)
                .getString();
        assertThat(actual).isEqualTo("eeeSTARTccc");
    }


    @Test
    public void getString_StartDelimiterIsInMiddle_StringAfterStartDelimiter() {
        String actual = SubString
                .create("uuuSTARTooo")
                .startDelimiter("START")
                .getString();
        assertThat(actual).isEqualTo("ooo");
    }


    @Test
    public void getString_StartDelimiterOccurrsTwice_StringAfterFirstStartDelimiter() {
        String actual = SubString
                .create("uuuSTARToooSTARTiii")
                .startDelimiter("START")
                .getString();
        assertThat(actual).isEqualTo("oooSTARTiii");
    }
    //------------------------------------------------------------------------
    // Test only end delimiter
    //------------------------------------------------------------------------


    @Test
    public void getString_TwoEndDelimiter_StringBeforeFirstOccurrence() {
        String actual = SubString
                .create("uuuENDoooENDiii")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getString_SecondEndDelimiterOfThree_StringBeforeSecondOccurrence() {
        String actual = SubString
                .create("uuuENDoooENDiiiENDeee")
                .endDelimiter("END")
                .endDelimiterOccurrence(2)
                .getString();
        assertThat(actual).isEqualTo("uuuENDooo");
    }


    @Test
    public void getString_RequestOccurrenceOfEndDelimiterThatDoesNotExist_WholeString() {
        String actual = SubString
                .create("uuuENDoooENDiiiENDeee")
                .endDelimiter("END")
                .endDelimiterOccurrence(40)
                .getString();
        assertThat(actual).isEqualTo("uuuENDoooENDiiiENDeee");
    }


    @Test
    public void getString_EndDelimiterOneCharLong_StringBeforeEndDelimiter() {
        String actual = SubString
                .create("uuuAooo")
                .endDelimiter("A")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getString_EndDelimiterLastChars_StringBeforeEndDelimiter() {
        String actual = SubString
                .create("uuuoooEND")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuuooo");
    }


    @Test
    public void getString_EndDelimiterMiddleOfString_StringBeforeEndDelimiter() {
        String actual = SubString
                .create("uuuENDooo")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getString_NoSuchEndDelimiter_WholeString() {
        String actual = SubString
                .create("uuuiiiooo")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("uuuiiiooo");
    }


    @Test
    public void getString_EndDelimiterIsFirstChars_EmptyString() {
        String actual = SubString
                .create("ENDuuukkkk")
                .endDelimiter("END")
                .getString();
        assertThat(actual).isEqualTo("");
    }
    //------------------------------------------------------------------------
    // MISC
    //------------------------------------------------------------------------


    @Test
    public void getString_NoDelimiters_InputStringUnaltered() {
        String actual = SubString
                .create("uuu")
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void toString_NoDelimiters_InputStringUnaltered() {
        String actual = SubString
                .create("uuu")
                .toString();
        assertThat(actual).isEqualTo("uuu");
    }


    @Test
    public void getBuilder_StringWithTwoLevelsOfDelimiters_StringBetween() {
        String input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        String actual = SubString
                .create(input)
                .startDelimiter("FIRST_START")
                .endDelimiter("FIRST_END")
                .newSubString()
                .startDelimiter("SECOND_START")
                .endDelimiter("SECOND_END")
                .getString();
        assertThat(actual).isEqualTo("cccc");
    }


    @Test
    public void getStr_SetString_ArgumentString() {
        String actual = SubString
                .create("uuu")
                .getStr()
                .getString();
        assertThat(actual).isEqualTo("uuu");
    }


}
