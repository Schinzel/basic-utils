package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

public class StrTest {
    @Test
    public void create() throws Exception {
        Assert.assertEquals("Str", Str.create().getClass().getSimpleName());
    }


    @Test
    public void a() throws Exception {
        Str str = Str.create().a("A").a("B");
        Assert.assertEquals("AB", str.getString());
    }


    @Test
    public void getThis() throws Exception {
        Str str1 = Str.create();
        Str str2 = str1.getThis();
        Assert.assertEquals(str1, str2);
    }


    @Test
    public void getLocale() throws Exception {
        Assert.assertEquals("en-US", Str.create().getLocale().toLanguageTag());
    }


    @Test
    public void getString() throws Exception {
        Str str = Str.create().a("A").a("B");
        Assert.assertEquals("AB", str.getString());
    }


    @Test
    public void toStringTest() throws Exception {
        Str str = Str.create().a("A").a("B");
        Assert.assertEquals("AB", str.toString());
    }
}