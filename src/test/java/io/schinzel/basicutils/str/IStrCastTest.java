package io.schinzel.basicutils.str;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import io.schinzel.basicutils.UTF8;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(JUnitQuickcheck.class)
public class IStrCastTest {
    private class StrCast extends AbstractIStr<StrCast> implements IStrCast<StrCast> {
        @Override
        public StrCast getThis() {
            return this;
        }
    }


    @Test
    public void castToInt_123456789_1234567894() {
        Integer actual = new StrCast().a("123456789").castToInt();
        Integer expected = 123456789;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void castToInt_abc_throwsException() {
        try {
            new StrCast().a("abc").castToInt();
            Assert.fail("Should throw exception");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).startsWith("Cannot cast");
        }
    }


    @Test
    public void castToDouble_12345678901234567890_12345678901234567890() {
        Double actual = new StrCast().a("12345678901234567890").castToDouble();
        Double expected = 12345678901234567890D;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void castToDouble_abc_throwsException() {
        try {
            new StrCast().a("abc").castToDouble();
            Assert.fail("Should throw exception");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).startsWith("Cannot cast");
        }
    }


    @Property
    public void castToUtf8_PropertyBased_Output(String input) {
        byte[] ab = new StrCast().a(input).castToUtf8Bytes();
        String output = UTF8.getString(ab);
        Assert.assertEquals(input, output);

    }


}