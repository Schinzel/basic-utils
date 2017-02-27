package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by schinzel on 2017-02-27.
 */
public class IStrStringTest {

    private class StrString extends AbstractIStr<StrString> implements IStrString<StrString> {
        @Override
        public StrString getThis() {
            return this;
        }
    }


    /**
     * Test appending a string builder
     * @throws Exception
     */
    @Test
    public void a() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("gibbon");
        StrString str = new StrString().a("chimp ").a(sb).a(" gorilla");
        Assert.assertEquals("chimp gibbon gorilla", str.getString());

    }


    @Test
    public void a1() throws Exception {
    }

}