package io.schinzel.basicutils.substringer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IndexFinderTest {

    @Test
    public void testGetPos_occurrences() {
        String string, substring;
        int startPos, occurrence, expected, actual;
        string = "___GG__GG__GG___";
        substring = "GG";
        startPos = 0;
        //
        occurrence = 1;
        expected = 3;
        actual = SubstringIndexFinder.getPos(string, substring, occurrence, startPos);
        assertEquals(expected, actual);
        //
        occurrence = 2;
        expected = 7;
        actual = SubstringIndexFinder.getPos(string, substring, occurrence, startPos);
        assertEquals(expected, actual);
        //
        occurrence = 3;
        expected = 11;
        actual = SubstringIndexFinder.getPos(string, substring, occurrence, startPos);
        assertEquals(expected, actual);
        //
        occurrence = 5;
        expected = -1;
        actual = SubstringIndexFinder.getPos(string, substring, occurrence, startPos);
        assertEquals(expected, actual);
        //
        occurrence = 20;
        expected = -1;
        actual = SubstringIndexFinder.getPos(string, substring, occurrence, startPos);
        assertEquals(expected, actual);
    }

}