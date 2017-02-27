package io.schinzel.basicutils.state;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 * Created by schinzel on 2017-02-26.
 */
public class StrTest {
    @Test
    public void testFormat_wholeNumbers() {
        Assert.assertEquals("1", Str.create().a(1).toString());
        Assert.assertEquals("123", Str.create().a(123).toString());
        Assert.assertEquals("123,456", Str.create().a(123456).toString());
        Assert.assertEquals("-1,234", Str.create().a(-1234).toString());
        Assert.assertEquals("123,456", Str.create().a(123456l).toString());
        Assert.assertEquals("123", Str.create().a(Integer.valueOf(123)).toString());
        Assert.assertEquals("123,456,789,123,456,789", Str.create().a(Long.valueOf("123456789123456789")).toString());
    }


    @Test
    public void testFormat_decimalNumbers() {
        Assert.assertEquals("123,456,789.12", Str.create().a(123456789.12345678d, 2).toString());
        Assert.assertEquals("1,234.46", Str.create().a(1234.456f, 2).toString());
        Assert.assertEquals("1,234.45", Str.create().a(1234.454f, 2).toString());
        Assert.assertEquals("1,234.50", Str.create().a(1234.5, 2).toString());
        Assert.assertEquals("1,234.00", Str.create().a(1234f, 2).toString());
        Assert.assertEquals("1.1235", Str.create().a(1.123456f, 4).toString());
        Assert.assertEquals("1.123", Str.create().a(1.123456f, 3).toString());
        Assert.assertEquals("1.12", Str.create().a(1.123456f, 2).toString());
        Assert.assertEquals("1.1", Str.create().a(1.123456f, 1).toString());
        Assert.assertEquals("1", Str.create().a(1.123456f, 0).toString());
        Assert.assertEquals("1,234.12346", Str.create().a(1234.123456789d, 5).toString());
    }


    @Test
    public void testPln() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Str.create().a("Monkey!").pln();
        Assert.assertEquals("Monkey!\n", outContent.toString());
        System.setOut(null);
    }
}