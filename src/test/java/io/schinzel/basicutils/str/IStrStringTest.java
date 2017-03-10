package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;


public class IStrStringTest {

    private class StrString extends AbstractIStr<StrString> implements IStrString<StrString> {
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
        Assert.assertEquals("chimp gibbon gorilla", str.getString());
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
        Assert.assertEquals("chimp gibbon gorilla", str2.getString());
    }


    @Test
    public void appendChar() {
        StrString str1 = new StrString().a("cat").a(' ').a("dog");
        Assert.assertEquals("cat dog", str1.getString());
    }
}