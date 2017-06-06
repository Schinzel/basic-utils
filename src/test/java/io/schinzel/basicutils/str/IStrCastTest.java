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
    public void castToLong_123456789012345678_123456789012345678() {
        Long actual = new StrCast().a("123456789012345678").castToLong();
        Long expected = 123456789012345678l;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void castToLong_abc_throwsException() {
        try {
            new StrCast().a("abc").castToLong();
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