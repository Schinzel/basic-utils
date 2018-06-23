package io.schinzel.basicutils.str;

import io.schinzel.basicutils.FunnyChars;
import io.schinzel.basicutils.UTF8;
import org.junit.Assert;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class IStrCastTest {
    private class StrCast extends AbstractIStr<StrCast> implements IStrCast<StrCast> {
        @Override
        public StrCast getThis() {
            return this;
        }
    }


    @Test
    public void castToInt_123456789_1234567894() {
        Integer actual = new StrCast().a("123456789").asInt();
        Integer expected = 123456789;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void castToInt_abc_throwsException() {
        try {
            new StrCast().a("abc").asInt();
            Assert.fail("Should throw exception");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).startsWith("Cannot cast");
        }
    }


    @Test
    public void castToLong_123456789012345678_123456789012345678() {
        Long actual = new StrCast().a("123456789012345678").asLong();
        Long expected = 123456789012345678l;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void castToLong_abc_throwsException() {
        try {
            new StrCast().a("abc").asLong();
            Assert.fail("Should throw exception");
        } catch (RuntimeException ex) {
            assertThat(ex.getMessage()).startsWith("Cannot cast");
        }
    }


    @Test
    public void castToUtf8_PropertyBased_Output() {
        for (FunnyChars funnyChars : FunnyChars.values()) {
            String input = funnyChars.getString();
            byte[] ab = new StrCast().a(input).asUtf8Bytes();
            String output = UTF8.getString(ab);
            Assert.assertEquals(input, output);
        }

    }


}