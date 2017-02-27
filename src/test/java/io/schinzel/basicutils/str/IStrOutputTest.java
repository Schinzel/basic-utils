package io.schinzel.basicutils.str;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by schinzel on 2017-02-27.
 */
public class IStrOutputTest {

    private class IStrString extends AbstractIStr<IStrString> implements IStrOutput<IStrString> {
        @Override
        public IStrString getThis() {
            return this;
        }
    }


    @Test
    public void pln() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new IStrString().a("Monkey!").pln();
        Assert.assertEquals("Monkey!\n", outContent.toString());
        System.setOut(null);
    }


    @Test
    public void writeToFile() throws Exception {

    }


    @Test
    public void writeToFile1() throws Exception {

    }

}