package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by schinzel on 2017-02-27.
 */
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