package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class IStrStringTest {

    private static class StrString extends AbstractIStr<StrString> implements IStrString<StrString> {
        @Override
        public StrString getThis() {
            return this;
        }
    }


    /**
     * Test appending a string builder
     */
    @Test
    public void appendStringBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append("gibbon");
        StrString str = new StrString().a("chimp ").a(sb).a(" gorilla");
        Assert.assertEquals("chimp gibbon gorilla", str.asString());
    }


    /**
     * Test appending another Str
     */
    @Test
    public void appendStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("gibbon");
        StrString str1 = new StrString().a(sb);
        StrString str2 = new StrString().a("chimp ").a(str1).a(" gorilla");
        Assert.assertEquals("chimp gibbon gorilla", str2.asString());
    }


    @Test
    public void appendChar() {
        StrString str1 = new StrString().a("cat").a(' ').a("dog");
        Assert.assertEquals("cat dog", str1.asString());
    }


    @Test
    public void a_FloatPrimitive_ShouldBeCorrectlyFormatted() {
        float f = 123.45678f;
        String actual = new StrString().a(f).asString();
        assertThat(actual).isEqualTo("123.45678");
    }


    @Test
    public void a_FloatObject_ShouldBeCorrectlyFormatted() {
        Float f = 123.45678f;
        String actual = new StrString().a(f).asString();
        assertThat(actual).isEqualTo("123.45678");
    }


    @Test
    public void a_FloatNull_NullString() {
        Float f = null;
        String actual = new StrString().a(f).asString();
        assertThat(actual).isEqualTo("null");
    }


    @Test
    public void a_FloatPrimitiveNonExact_ShouldBeCorrectlyFormatted() {
        float f = 1 / 3f;
        String actual = new StrString().a(f).asString();
        assertThat(actual).isEqualTo("0.33333334");
    }


    @Test
    public void a_DoublePrimitive_ShouldBeCorrectlyFormatted() {
        double d = 123.45678d;
        String actual = new StrString().a(d).asString();
        assertThat(actual).isEqualTo("123.45678");
    }


    @Test
    public void a_DoubleObject_ShouldBeCorrectlyFormatted() {
        Double d = 123.45678d;
        String actual = new StrString().a(d).asString();
        assertThat(actual).isEqualTo("123.45678");
    }


    @Test
    public void a_DoubleNull_ShouldBeCorrectlyFormatted() {
        Double d = null;
        String actual = new StrString().a(d).asString();
        assertThat(actual).isEqualTo("null");
    }


    @Test
    public void a_DoublePrimitiveNonExact_ShouldBeCorrectlyFormatted() {
        double d = 1 / 3d;
        String actual = new StrString().a(d).asString();
        assertThat(actual).isEqualTo("0.3333333333333333");
    }


    @Test
    public void a_LongPrimitive_ShouldBeCorrectlyFormatted() {
        long l = 123_456_789_123_456_789L;
        String actual = new StrString().a(l).asString();
        assertThat(actual).isEqualTo("123456789123456789");
    }


    @Test
    public void a_LongNull_NullString() {
        Long l = null;
        String actual = new StrString().a(l).asString();
        assertThat(actual).isEqualTo("null");
    }


    @Test
    public void a_LongObject_ShouldBeCorrectlyFormatted() {
        Long l = 123_456_789_123_456_789L;
        String actual = new StrString().a(l).asString();
        assertThat(actual).isEqualTo("123456789123456789");
    }


    @Test
    public void a_Int_ShouldBeCorrectlyFormatted() {
        int i = 123_456_789;
        String actual = new StrString().a(i).asString();
        assertThat(actual).isEqualTo("123456789");
    }


    @Test
    public void a_IntegerNull_NullString() {
        Integer i = null;
        String actual = new StrString().a(i).asString();
        assertThat(actual).isEqualTo("null");
    }


    @Test
    public void a_Integer_ShouldBeCorrectlyFormatted() {
        Integer i = 123_456_789;
        String actual = new StrString().a(i).asString();
        assertThat(actual).isEqualTo("123456789");
    }


    @Test
    public void a_BooleanObjectTrue_true() {
        Boolean b = Boolean.TRUE;
        String actual = new StrString().a(b).asString();
        assertThat(actual).isEqualTo("true");
    }


    @Test
    public void a_BooleanObjectFalse_false() {
        Boolean b = Boolean.FALSE;
        String actual = new StrString().a(b).asString();
        assertThat(actual).isEqualTo("false");
    }


    @Test
    public void a_BooleanObjectNull_nullString() {
        Boolean b = null;
        String actual = new StrString().a(b).asString();
        assertThat(actual).isEqualTo("null");
    }


    @Test
    public void a_BooleanPrimitiveTrue_true() {
        boolean b = true;
        String actual = new StrString().a(b).asString();
        assertThat(actual).isEqualTo("true");
    }


    @Test
    public void a_BooleanPrimitiveFalse_false() {
        boolean b = false;
        String actual = new StrString().a(b).asString();
        assertThat(actual).isEqualTo("false");
    }
}