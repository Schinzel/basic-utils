package io.schinzel.basicutils.state;

import io.schinzel.basicutils.state.NumberFormatter;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author schinzel
 */
public class NumberFormatterTest {

    @Test
    public void testFormat_wholeNumbers() {
        Assert.assertEquals("1", NumberFormatter.format(1));
        Assert.assertEquals("123", NumberFormatter.format(123));
        Assert.assertEquals("123 456", NumberFormatter.format(123456));
        Assert.assertEquals("-1 234", NumberFormatter.format(-1234));
        Assert.assertEquals("123 456", NumberFormatter.format(123456l));
        Assert.assertEquals("123", NumberFormatter.format(Integer.valueOf(123)));
        Assert.assertEquals("123 456 789 123 456 789", NumberFormatter.format(Long.valueOf("123456789123456789")));
    }


    @Test
    public void testFormat_decimalNumbers() {
        Assert.assertEquals("123 456 789.12", NumberFormatter.format(123456789.12345678d, 2));
        Assert.assertEquals("1 234.46", NumberFormatter.format(1234.456f, 2));
        Assert.assertEquals("1 234.45", NumberFormatter.format(1234.454f, 2));
        Assert.assertEquals("1 234.50", NumberFormatter.format(1234.5, 2));
        Assert.assertEquals("1 234.00", NumberFormatter.format(1234f, 2));
        Assert.assertEquals("1.1235", NumberFormatter.format(1.123456f, 4));
        Assert.assertEquals("1.123", NumberFormatter.format(1.123456f, 3));
        Assert.assertEquals("1.12", NumberFormatter.format(1.123456f, 2));
        Assert.assertEquals("1.1", NumberFormatter.format(1.123456f, 1));
        Assert.assertEquals("1", NumberFormatter.format(1.123456f, 0));
        Assert.assertEquals("1 234.12346", NumberFormatter.format(1234.123456789d, 5));
    }


}
