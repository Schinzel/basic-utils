package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

public class IStrWhitespaceTest {

    private class StrWhitespace extends AbstractIStr<StrWhitespace> implements IStrWhitespace<StrWhitespace> {

        @Override
        public StrWhitespace getThis() {
            return this;
        }
    }


    @Test
    public void aws() throws Exception {
        StrWhitespace str = new StrWhitespace().aws(IStrWhitespace.WS.SPACE);
        Assert.assertEquals(" ", str.asString());
    }


    @Test
    public void anl() throws Exception {
        StrWhitespace str = new StrWhitespace().anl();
        Assert.assertEquals("\n", str.asString());
    }


    @Test
    public void anlStr() throws Exception {
        StrWhitespace str = new StrWhitespace().anl("A");
        Assert.assertEquals("A\n", str.asString());
    }


    @Test
    public void asp() throws Exception {
        StrWhitespace str = new StrWhitespace().asp();
        Assert.assertEquals(" ", str.asString());
    }


    @Test
    public void aspStr() throws Exception {
        StrWhitespace str = new StrWhitespace().asp("A");
        Assert.assertEquals("A ", str.asString());
    }


    @Test
    public void atab() throws Exception {
        StrWhitespace str = new StrWhitespace().atab();
        Assert.assertEquals("\t", str.asString());
    }


    @Test
    public void atabStr() throws Exception {
        StrWhitespace str = new StrWhitespace().atab("A");
        Assert.assertEquals("A\t", str.asString());
    }


    @Test
    public void acrlf_NoArg_WindowsLineBreak() {
        StrWhitespace str = new StrWhitespace().acrlf();
        Assert.assertEquals("\r\n", str.asString());
    }

    @Test
    public void acrlf_A_WindowsLineBreak() {
        StrWhitespace str = new StrWhitespace().acrlf("A");
        Assert.assertEquals("A\r\n", str.asString());
    }
}