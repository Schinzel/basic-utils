package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by schinzel on 2017-02-26.
 */
public class IStrNumbersTest {

    private class IStrNumber extends AbstractIStr<IStrNumber> implements IStrNumbers<IStrNumber> {
        @Override
        public IStrNumber getThis() {
            return this;
        }
    }


    @Test
    public void testFormat_wholeNumbers() {
        Assert.assertEquals("1", new IStrNumber().a(1).getString());
        Assert.assertEquals("123", new IStrNumber().a(123).getString());
        Assert.assertEquals("123,456", new IStrNumber().a(123456).getString());
        Assert.assertEquals("-1,234", new IStrNumber().a(-1234).getString());
        Assert.assertEquals("123,456", new IStrNumber().a(123456l).getString());
        Assert.assertEquals("123", new IStrNumber().a(Integer.valueOf(123)).getString());
        Assert.assertEquals("123,456,789,123,456,789", new IStrNumber().a(Long.valueOf("123456789123456789")).getString());
    }


    @Test
    public void testFormat_decimalNumbers() {
        Assert.assertEquals("123,456,789.12", new IStrNumber().a(123456789.12345678d, 2).getString());
        Assert.assertEquals("1,234.46", new IStrNumber().a(1234.456f, 2).getString());
        Assert.assertEquals("1,234.45", new IStrNumber().a(1234.454f, 2).getString());
        Assert.assertEquals("1,234.50", new IStrNumber().a(1234.5, 2).getString());
        Assert.assertEquals("1,234.00", new IStrNumber().a(1234f, 2).getString());
        Assert.assertEquals("1.1235", new IStrNumber().a(1.123456f, 4).getString());
        Assert.assertEquals("1.123", new IStrNumber().a(1.123456f, 3).getString());
        Assert.assertEquals("1.12", new IStrNumber().a(1.123456f, 2).getString());
        Assert.assertEquals("1.1", new IStrNumber().a(1.123456f, 1).getString());
        Assert.assertEquals("1", new IStrNumber().a(1.123456f, 0).getString());
        Assert.assertEquals("1,234.12346", new IStrNumber().a(1234.123456789d, 5).getString());
    }

}