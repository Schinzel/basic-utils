package io.schinzel.basicutils;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Schinzel
 */
public class SubStringerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testGetQueryString() {
        String input, queryString, url;
        //Test basics
        input = "http://www.kollektiva.se/index.html?key1=val1&key2=val2";
        queryString = SubStringer.create(input)
                .start("?")
                .toString();
        assertEquals("key1=val1&key2=val2", queryString);
        url = SubStringer.create(input)
                .end("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test ? but no data
        input = "http://www.kollektiva.se/index.html?";
        queryString = SubStringer.create(input)
                .start("?")
                .toString();
        assertEquals("", queryString);
        url = SubStringer.create(input)
                .end("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test no ?
        input = "http://www.kollektiva.se/index.html";
        queryString = SubStringer.create(input)
                .start("?")
                .toString();
        assertEquals("", queryString);
        input = "http://www.kollektiva.se/index.html";
        url = SubStringer.create(input)
                .end("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
    }


    @Test
    public void testThatArgumentStringIsNotChanged() {
        String input = "hhhhAPAuuuAPAkkkk";
        String output = SubStringer.create(input)
                .start("APA")
                .end("APA")
                .toString();
        assertEquals("hhhhAPAuuuAPAkkkk", input);
        assertEquals("uuu", output);
    }


    @Test
    public void testGetSubStringer() {
        String input, output;
        //
        input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        output = SubStringer.create(input)
                .start("FIRST_START")
                .end("FIRST_END")
                .getSubStringer()
                .start("SECOND_START")
                .end("SECOND_END")
                .toString();
        assertEquals("cccc", output);
    }


    @Test
    public void testStartAndEnd() {
        String input, output;
        //
        input = "ddddAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .start("APA")
                .end("APA")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddAuuuAkkkk";
        output = SubStringer.create(input)
                .start("A")
                .end("A")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuENDkkkk";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuENDkkkk";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("u", output);
        //
        input = "STARTEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("", output);
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testStartAndEnd_multipleOccurencesOfDelimiters() {
        String input, output;
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrENDddddSTARTuuuENDyyyEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubStringer.create(input)
                .start("START")
                .end("END")
                .toString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testOnlyStartDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubStringer.create(input)
                .start("APA")
                .toString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .start("APA")
                .toString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPA";
        output = SubStringer.create(input)
                .start("APA")
                .toString();
        assertEquals("", output);
        //
        input = "dddddAuuuAkkkk";
        output = SubStringer.create(input)
                .start("A")
                .toString();
        assertEquals("uuuAkkkk", output);
    }


    @Test
    public void testOnlyEndDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubStringer.create(input)
                .end("APA")
                .toString();
        assertEquals("", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .end("APA")
                .toString();
        assertEquals("eeee", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .end("kkkk")
                .toString();
        assertEquals("eeeeAPAuuuAPA", output);
        //
        input = "eeeeQuuuQkkkk";
        output = SubStringer.create(input)
                .end("Q")
                .toString();
        assertEquals("eeee", output);
    }


    @Test
    public void testNeitherStartNorEnd() {
        String input = "APAuuuAPAkkkk";
        exception.expect(RuntimeException.class);
        exception.expectMessage("Incorrect usage. Neither start nor end delimiter has been set.");
        SubStringer.create(input).toString();
    }

}
