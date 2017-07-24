package io.schinzel.basicutils.substring;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class IndexFinderTest {


    @Test
    public void getSubstringPosition_Occurrence1_3() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(1)
                .startPos(0)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(3);
    }


    @Test
    public void getSubstringPosition_Occurrence2_7() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(2)
                .startPos(0)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(7);
    }


    @Test
    public void getSubstringPosition_Occurrence3_7() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(3)
                .startPos(0)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(11);
    }


    @Test
    public void getSubstringPosition_OccurrenceDoesNotExist_Minus1() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(4)
                .startPos(0)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(-1);
    }


    @Test
    public void getSubstringPosition_Occurrence2StartPos_7() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(2)
                .startPos(6)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(11);
    }


    @Test
    public void getSubstringPosition_OccurrenceMinus4_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                SubstringIndexFinder.builder()
                        .string("___GG__GG__GG___")
                        .subString("GG")
                        .occurrence(-4)
                        .startPos(0)
                        .build()
        ).withMessageContaining("The value -4 in variable 'occurrence' is too small.");
    }


    @Test
    public void getSubstringPosition_StartPosMinus1_ThrowsException() {
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() ->
                SubstringIndexFinder.builder()
                        .string("___GG__GG__GG___")
                        .subString("GG")
                        .occurrence(1)
                        .startPos(-1)
                        .build()
        ).withMessageContaining("The value -1 in variable 'startPos' is too small.");
    }


    @Test
    public void getSubstringPosition_LastOccurrence_11() {
        int actualPos = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(SubstringIndexFinder.LAST_OCCURRENCE)
                .startPos(0)
                .build()
                .getSubstringPosition();
        assertThat(actualPos).isEqualTo(11);
    }


    @Test
    public void isSubstringFound_ExistingSubString_True() {
        boolean actual = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(1)
                .startPos(0)
                .build()
                .isSubstringFound();
        assertThat(actual).isTrue();
    }


    @Test
    public void isSubstringFound_NonExistingOccurrence_False() {
        boolean actual = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GG")
                .occurrence(11)
                .startPos(0)
                .build()
                .isSubstringFound();
        assertThat(actual).isFalse();
    }


    @Test
    public void isSubstringFound_NonExistingSubString_False() {
        boolean actual = SubstringIndexFinder.builder()
                .string("___GG__GG__GG___")
                .subString("GGGGGGG")
                .occurrence(1)
                .startPos(0)
                .build()
                .isSubstringFound();
        assertThat(actual).isFalse();
    }

}