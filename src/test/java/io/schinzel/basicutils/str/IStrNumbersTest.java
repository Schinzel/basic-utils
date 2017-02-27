package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by schinzel on 2017-02-26.
 */
public class IStrNumbersTest {

    private class StrNumber extends AbstractIStr<StrNumber> implements IStrNumbers<StrNumber> {
        @Override
        public StrNumber getThis() {
            return this;
        }
    }


    @Test
    public void testFormat_wholeNumbers() {
        Assert.assertEquals("1", new StrNumber().a(1).getString());
        Assert.assertEquals("123", new StrNumber().a(123).getString());
        Assert.assertEquals("123,456", new StrNumber().a(123456).getString());
        Assert.assertEquals("-1,234", new StrNumber().a(-1234).getString());
        Assert.assertEquals("123,456", new StrNumber().a(123456l).getString());
        Assert.assertEquals("123", new StrNumber().a(Integer.valueOf(123)).getString());
        Assert.assertEquals("123,456,789,123,456,789", new StrNumber().a(Long.valueOf("123456789123456789")).getString());
    }


    @Test
    public void testFormat_decimalNumbers() {
        Assert.assertEquals("123,456,789.12", new StrNumber().a(123456789.12345678d, 2).getString());
        Assert.assertEquals("1,234.46", new StrNumber().a(1234.456f, 2).getString());
        Assert.assertEquals("1,234.45", new StrNumber().a(1234.454f, 2).getString());
        Assert.assertEquals("1,234.50", new StrNumber().a(1234.5, 2).getString());
        Assert.assertEquals("1,234.00", new StrNumber().a(1234f, 2).getString());
        Assert.assertEquals("1.1235", new StrNumber().a(1.123456f, 4).getString());
        Assert.assertEquals("1.123", new StrNumber().a(1.123456f, 3).getString());
        Assert.assertEquals("1.12", new StrNumber().a(1.123456f, 2).getString());
        Assert.assertEquals("1.1", new StrNumber().a(1.123456f, 1).getString());
        Assert.assertEquals("1", new StrNumber().a(1.123456f, 0).getString());
        Assert.assertEquals("1,234.12346", new StrNumber().a(1234.123456789d, 5).getString());
    }

}