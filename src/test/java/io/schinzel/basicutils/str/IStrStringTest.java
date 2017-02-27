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
        Str str1 = Str.create().a("gibbon");
        Str str2 = Str.create().a("chimp ").a(str1).a(" gorilla");
        Assert.assertEquals("chimp gibbon gorilla", str2.getString());
    }
}