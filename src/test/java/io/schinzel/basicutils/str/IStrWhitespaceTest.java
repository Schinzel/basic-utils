package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by schinzel on 2017-02-27.
 */
public class IStrWhitespaceTest {

    private class StrWhitespace extends AbstractIStr<StrWhitespace> implements IStrWhitespace<StrWhitespace> {

        @Override
        public StrWhitespace getThis() {
            return this;
        }
    }


    @Test
    public void aws() throws Exception {
        Str str = Str.create().a("A").aws(IStrWhitespace.WS.SPACE).a("B");
        Assert.assertEquals("A B", str.getString());
    }


    @Test
    public void anl() throws Exception {
        Str str = Str.create().a("A").anl().a("B");
        Assert.assertEquals("A\nB", str.getString());
    }


    @Test
    public void asp() throws Exception {
        Str str = Str.create().a("A").asp().a("B");
        Assert.assertEquals("A B", str.getString());
    }


    @Test
    public void atab() throws Exception {
        Str str = Str.create().a("A").atab().a("B");
        Assert.assertEquals("A\tB", str.getString());
    }
}