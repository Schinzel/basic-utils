package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

public class IStrFormatNumbersTest {

    private class StrNumber extends AbstractIStr<StrNumber> implements IStrFormatNumbers<StrNumber> {
        @Override
        public StrNumber getThis() {
            return this;
        }
    }


    @Test
    public void testFormat_wholeNumbers() {
        Assert.assertEquals("1", new StrNumber().af(1).asString());
        Assert.assertEquals("123", new StrNumber().af(123).asString());
        Assert.assertEquals("123,456", new StrNumber().af(123456).asString());
        Assert.assertEquals("-1,234", new StrNumber().af(-1234).asString());
        Assert.assertEquals("123,456", new StrNumber().af(123456l).asString());
        Assert.assertEquals("123", new StrNumber().af(Integer.valueOf(123)).asString());
        Assert.assertEquals("123,456,789,123,456,789", new StrNumber().af(Long.valueOf("123456789123456789")).asString());
    }


    @Test
    public void testFormat_decimalNumbers() {
        Assert.assertEquals("123,456,789.12", new StrNumber().af(123456789.12345678d, 2).asString());
        Assert.assertEquals("1,234.46", new StrNumber().af(1234.456f, 2).asString());
        Assert.assertEquals("1,234.45", new StrNumber().af(1234.454f, 2).asString());
        Assert.assertEquals("1,234.50", new StrNumber().af(1234.5, 2).asString());
        Assert.assertEquals("1,234.00", new StrNumber().af(1234f, 2).asString());
        Assert.assertEquals("1.1235", new StrNumber().af(1.123456f, 4).asString());
        Assert.assertEquals("1.123", new StrNumber().af(1.123456f, 3).asString());
        Assert.assertEquals("1.12", new StrNumber().af(1.123456f, 2).asString());
        Assert.assertEquals("1.1", new StrNumber().af(1.123456f, 1).asString());
        Assert.assertEquals("1", new StrNumber().af(1.123456f, 0).asString());
        Assert.assertEquals("1,234.12346", new StrNumber().af(1234.123456789d, 5).asString());
    }

}