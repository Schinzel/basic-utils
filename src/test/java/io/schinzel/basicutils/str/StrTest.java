package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

public class StrTest {

    @Test
    public void ifTrue_True_SringShouldNotBeAdded() {
        String actual = Str.create()
                .a("A")
                .ifTrue(true)
                .a("B")
                .endIf()
                .a("C").toString();
        String expected = "AC";
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void ifTrue_False_SringShouldNotBeAdded() {
        String actual = Str.create()
                .a("A")
                .ifTrue(false)
                .a("B")
                .endIf()
                .a("C").toString();
        String expected = "ABC";
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void create() throws Exception {
        Assert.assertEquals("Str", Str.create().getClass().getSimpleName());
    }


    @Test
    public void create_withArg() throws Exception {
        Str str = Str.create("A").a("B");
        Assert.assertEquals("AB", str.getString());
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