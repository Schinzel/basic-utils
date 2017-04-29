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


    @Test
    public void testGetQueryString() {
        String input, queryString, url;
        //Test basics
        input = "http://www.kollektiva.se/index.html?key1=val1&key2=val2";
        queryString = SubString.builder()
                .string(input)
                .startDelimiter("?")
                .build()
                .getString();
        assertEquals("key1=val1&key2=val2", queryString);
        url = SubString.builder()
                .string(input)
                .endDelimiter("?")
                .build()
                .getString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test ? but no data
        input = "http://www.kollektiva.se/index.html?";
        queryString = SubString.builder()
                .string(input)
                .startDelimiter("?")
                .build()
                .getString();
        assertEquals("", queryString);
        url = SubString.builder()
                .string(input)
                .endDelimiter("?")
                .build().getString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test no ?
        input = "http://www.kollektiva.se/index.html";
        queryString = SubString.builder()
                .string(input)
                .startDelimiter("?")
                .build().getString();
        assertEquals("", queryString);
        input = "http://www.kollektiva.se/index.html";
        url = SubString.builder()
                .string(input)
                .endDelimiter("?")
                .build().getString();
        assertEquals("http://www.kollektiva.se/index.html", url);
    }


    @Test
    public void testGetSubStringer() {
        String input, output;
        //
        input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        output = SubString.builder()
                .string(input)
                .startDelimiter("FIRST_START")
                .endDelimiter("FIRST_END")
                .build()
                .getBuilder()
                .startDelimiter("SECOND_START")
                .endDelimiter("SECOND_END")
                .build()
                .getString();
        assertEquals("cccc", output);
    }


    @Test
    public void testStartAndEnd() {
        String input, output;
        //
        input = "ddddAPAuuuAPAkkkk";
        output = SubString.builder()
                .string(input)
                .startDelimiter("APA")
                .endDelimiter("APA")
                .build()
                .getString();
        assertEquals("uuu", output);
        //
        input = "ddddAuuuAkkkk";
        output = SubString.builder()
                .string(input)
                .startDelimiter("A")
                .endDelimiter("A")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuENDkkkk";
        output = SubString.builder()
                .string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuENDkkkk";
        output = SubString.builder()
                .string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "STARTuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("u", output);
        //
        input = "STARTEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("", output);
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testStartAndEnd_multipleOccurencesOfDelimiters() {
        String input, output;
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "rrrrENDddddSTARTuuuENDyyyEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubString.builder().string(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .build().getString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testOnlyStartDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubString.builder().string(input)
                .startDelimiter("APA")
                .build().getString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPAuuuAPAkkkk";
        output = SubString.builder().string(input)
                .startDelimiter("APA")
                .build().getString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPA";
        output = SubString.builder().string(input)
                .startDelimiter("APA")
                .build().getString();
        assertEquals("", output);
        //
        input = "dddddAuuuAkkkk";
        output = SubString.builder().string(input)
                .startDelimiter("A")
                .build().getString();
        assertEquals("uuuAkkkk", output);
    }


    @Test
    public void testOnlyEndDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubString.builder().string(input)
                .endDelimiter("APA")
                .build()
                .getString();
        assertEquals("", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubString.builder().string(input)
                .endDelimiter("APA")
                .build()
                .getString();
        assertEquals("eeee", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubString.builder().string(input)
                .endDelimiter("kkkk")
                .build()
                .getString();
        assertEquals("eeeeAPAuuuAPA", output);
        //
        input = "eeeeQuuuQkkkk";
        output = SubString.builder().string(input)
                .endDelimiter("Q")
                .build()
                .getString();
        assertEquals("eeee", output);
    }


}
