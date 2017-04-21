package io.schinzel.basicutils.substringer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Schinzel
 */
public class SubStringerTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testContains() {
        SubStringer subStringer = SubStringer.create("This is a string");
        assertTrue(subStringer.contains("is"));
        assertFalse(subStringer.contains("I_do_not_exists"));
    }


    @Test
    public void testGetQueryString() {
        String input, queryString, url;
        //Test basics
        input = "http://www.kollektiva.se/index.html?key1=val1&key2=val2";
        queryString = SubStringer.create(input)
                .startDelimiter("?")
                .toString();
        assertEquals("key1=val1&key2=val2", queryString);
        url = SubStringer.create(input)
                .endDelimiter("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test ? but no data
        input = "http://www.kollektiva.se/index.html?";
        queryString = SubStringer.create(input)
                .startDelimiter("?")
                .toString();
        assertEquals("", queryString);
        url = SubStringer.create(input)
                .endDelimiter("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
        //Test no ?
        input = "http://www.kollektiva.se/index.html";
        queryString = SubStringer.create(input)
                .startDelimiter("?")
                .toString();
        assertEquals("", queryString);
        input = "http://www.kollektiva.se/index.html";
        url = SubStringer.create(input)
                .endDelimiter("?")
                .toString();
        assertEquals("http://www.kollektiva.se/index.html", url);
    }


    @Test
    public void testGetSubStringer() {
        String input, output;
        //
        input = "aaaaFIRST_STARTbbbbbSECOND_STARTccccSECOND_ENDddddFIRST_ENDeeee";
        output = SubStringer.create(input)
                .startDelimiter("FIRST_START")
                .endDelimiter("FIRST_END")
                .getSubStringer()
                .startDelimiter("SECOND_START")
                .endDelimiter("SECOND_END")
                .toString();
        assertEquals("cccc", output);
    }


    @Test
    public void testStartAndEnd() {
        String input, output;
        //
        input = "ddddAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .startDelimiter("APA")
                .endDelimiter("APA")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddAuuuAkkkk";
        output = SubStringer.create(input)
                .startDelimiter("A")
                .endDelimiter("A")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuENDkkkk";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuENDkkkk";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "ddddSTARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "STARTuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("u", output);
        //
        input = "STARTEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("", output);
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testStartAndEnd_multipleOccurencesOfDelimiters() {
        String input, output;
        //
        input = "rrrrENDddddSTARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrENDddddSTARTuuuENDyyyEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("uuu", output);
        //
        input = "rrrrSTARTddddSTARTuuuEND";
        output = SubStringer.create(input)
                .startDelimiter("START")
                .endDelimiter("END")
                .toString();
        assertEquals("ddddSTARTuuu", output);
    }


    @Test
    public void testOnlyStartDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubStringer.create(input)
                .startDelimiter("APA")
                .toString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .startDelimiter("APA")
                .toString();
        assertEquals("uuuAPAkkkk", output);
        //
        input = "dddddAPA";
        output = SubStringer.create(input)
                .startDelimiter("APA")
                .toString();
        assertEquals("", output);
        //
        input = "dddddAuuuAkkkk";
        output = SubStringer.create(input)
                .startDelimiter("A")
                .toString();
        assertEquals("uuuAkkkk", output);
    }


    @Test
    public void testOnlyEndDelimiter() {
        String input, output;
        //
        input = "APAuuuAPAkkkk";
        output = SubStringer.create(input)
                .endDelimiter("APA")
                .toString();
        assertEquals("", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .endDelimiter("APA")
                .toString();
        assertEquals("eeee", output);
        //
        input = "eeeeAPAuuuAPAkkkk";
        output = SubStringer.create(input)
                .endDelimiter("kkkk")
                .toString();
        assertEquals("eeeeAPAuuuAPA", output);
        //
        input = "eeeeQuuuQkkkk";
        output = SubStringer.create(input)
                .endDelimiter("Q")
                .toString();
        assertEquals("eeee", output);
    }


    @Test
    public void testStart_emptyString() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'StartDelimiter' cannot be empty");
        SubStringer.create("any input").startDelimiter("").toString();
    }


    @Test
    public void testStart_nullString() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'StartDelimiter' cannot be empty");
        SubStringer.create("any input").startDelimiter(null).toString();
    }


    @Test
    public void testEnd_emptyString() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'EndDelimiter' cannot be empty");
        SubStringer.create("any input").endDelimiter("").toString();
    }


    @Test
    public void testEnd_nullString() {
        exception.expect(RuntimeException.class);
        exception.expectMessage("Argument 'EndDelimiter' cannot be empty");
        SubStringer.create("any input").endDelimiter(null).toString();
    }
}
