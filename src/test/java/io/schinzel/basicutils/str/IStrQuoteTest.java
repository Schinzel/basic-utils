package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;


public class IStrQuoteTest {


    private class StrQuote extends AbstractIStr<StrQuote> implements IStrQuote<StrQuote> {
        @Override
        public StrQuote getThis() {
            return this;
        }
    }


    @Test
    public void aqTest() {
        String result = new StrQuote().aq("A").asString();
        Assert.assertEquals("'A'", result);
        result = new StrQuote().aq(null).asString();
        Assert.assertEquals("''", result);
    }


    @Test
    public void aqTestCharQuote() {
        String result = new StrQuote().aq("A", '!').asString();
        Assert.assertEquals("!A!", result);
        result = new StrQuote().aq(null, '!').asString();
        Assert.assertEquals("!!", result);
    }


    @Test
    public void aqTestStringQuote() {
        String result = new StrQuote().aq("AAA", "cat").asString();
        Assert.assertEquals("catAAAcat", result);
        result = new StrQuote().aq(null, "cat").asString();
        Assert.assertEquals("catcat", result);
    }


    @Test
    public void aqTestCharStartEndQuote() {
        String result = new StrQuote().aq("A", '[', ']').asString();
        Assert.assertEquals("[A]", result);
        result = new StrQuote().aq(null, '[', ']').asString();
        Assert.assertEquals("[]", result);
    }

    @Test
    public void aqTestStringStartEndQuote() {
        String result = new StrQuote().aq("A", "start", "end").asString();
        Assert.assertEquals("startAend", result);
        result = new StrQuote().aq(null, "start", "end").asString();
        Assert.assertEquals("startend", result);
    }

}